package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AppointmentFeedback (
    @SerializedName("feedbackId")
    val feedbackId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("appointmentId")
    val appointmentId: Int?,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("comments")
    val comments: String,

    @SerializedName("dateOfFeedback")
    val dateOfFeedback: Date
)