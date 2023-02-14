package com.dldmswo1209.noteapp.data.model

import android.os.Parcelable
import com.google.firebase.firestore.ServerTimestamp
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Note(
    var id: String = "",
    val text: String = "",
    @ServerTimestamp
    val date: Date = Date()
) : Parcelable