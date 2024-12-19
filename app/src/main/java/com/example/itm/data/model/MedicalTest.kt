package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MedicalTest(
    @SerializedName("testId")
    val testId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("testName")
    val testName: String,

    @SerializedName("dateOfTest")
    val dateOfTest: Date,

    @SerializedName("results")
    val results: String,

    @SerializedName("labInformation")
    val labInformation: String
)