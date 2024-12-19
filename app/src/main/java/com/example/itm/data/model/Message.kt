package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Message(
    @SerializedName("messageId")
    val messageId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("dateSent")
    val dateSent: Date,

    @SerializedName("messageContent")
    val messageContent: String,

    @SerializedName("replyStatus")
    val replyStatus: ReplyStatus
)