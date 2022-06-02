package com.example.chatbotcalledbob

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(val message: String, val id: String) : Parcelable {
}