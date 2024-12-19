package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class PatientNotification(
    @SerializedName("notificationId")
    val notificationId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("dateOfNotification")
    val dateOfNotification: Date,

    @SerializedName("type")
    val type: String,

    @SerializedName("message")
    val message: String
)

