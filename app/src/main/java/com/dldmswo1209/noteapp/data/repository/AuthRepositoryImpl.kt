package com.dldmswo1209.noteapp.data.repository

import com.dldmswo1209.noteapp.data.model.User
import com.dldmswo1209.noteapp.util.FireStoreTables
import com.dldmswo1209.noteapp.util.UiState
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
    private val database: FirebaseFirestore
) : AuthRepository {

    override fun registerUser(
        email: String,
        password: String,
        user: User,
        result: (UiState<String>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    updateUserInfo(user){state->
                        when(state){
                            is UiState.Success -> {
                                result(
                                    UiState.Success("User register successfully!")
                                )
                            }
                            is UiState.Failure -> {
                                result(UiState.Failure(state.error))
                            }
                            else -> {}
                        }

                    }
                    result(
                        UiState.Success("User register successfully!")
                    )
                }else{
                    try{
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    }catch (e: FirebaseAuthWeakPasswordException){
                        result(UiState.Failure("Authentication failed, Password should be at least 6 characters"))
                    }catch (e: FirebaseAuthInvalidCredentialsException){
                        result(UiState.Failure("Authentication failed, Invalid email entered"))
                    }catch(e: FirebaseAuthUserCollisionException){
                        result(UiState.Failure("Authentication failed, Email already registered"))
                    }catch(e: Exception){
                        result(UiState.Failure(e.message))
                    }
                }
            }
            .addOnFailureListener {
                result(
                    UiState.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override fun updateUserInfo(user: User, result: (UiState<String>) -> Unit) {
        val document = database.collection(FireStoreTables.USER).document()
        user.id = document.id

        document
            .set(user)
            .addOnSuccessListener {
                result(
                    UiState.Success("user has been update successfully")
                )
            }
            .addOnFailureListener {
                result(
                    UiState.Failure(it.localizedMessage)
                )
            }

    }

    override fun loginUser(
        email: String,
        password: String,
        result: (UiState<String>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task->
                if(task.isSuccessful){
                    result(UiState.Success("Login successfully!"))
                }else{
                    result(UiState.Failure("Authentication failed, ch;eck email and password"))
                }
            }
            .addOnFailureListener {
                result(UiState.Failure("Authentication failed, ch;eck email and password"))
            }
    }


    override fun forgotPassword(user: User, result: (UiState<String>) -> Unit) {

    }

}