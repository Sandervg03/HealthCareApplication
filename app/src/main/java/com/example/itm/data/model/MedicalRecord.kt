package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MedicalRecord(
    @SerializedName("recordId")
    val recordId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("dateOfRecord")
    val dateOfRecord: Date,

    @SerializedName("diagnosis")
    val diagnosis: String,

    @SerializedName("treatment")
    val treatment: String,

    @SerializedName("medicationsPrescribed")
    val medicationsPrescribed: String,

    @SerializedName("testResults")
    val testResults: String,

    @SerializedName("followUpRecommendations")
    val followUpRecommendations: String
)