package com.dldmswo1209.noteapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dldmswo1209.noteapp.data.model.User
import com.dldmswo1209.noteapp.data.repository.AuthRepository
import com.dldmswo1209.noteapp.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    private var _register = MutableLiveData< UiState<String>>()
    val register: LiveData<UiState<String>> = _register

    private var _login = MutableLiveData< UiState<String>>()
    val login: LiveData<UiState<String>> = _login

    fun register(
        email: String,
        password: String,
        user: User
    ) {
        _register.value = UiState.Loading
        repository.registerUser(
            email = email,
            password = password,
            user = user
        ){ _register.postValue(it) }
    }

    fun login(
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading
        repository.loginUser(email, password){ _login.postValue(it) }
    }
}