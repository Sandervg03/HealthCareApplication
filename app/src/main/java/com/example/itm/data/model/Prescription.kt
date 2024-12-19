package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Prescription(
    @SerializedName("prescriptionId")
    val prescriptionId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("medicationName")
    val medicationName: String,

    @SerializedName("medicationDosage")
    val medicationDosage: String,

    @SerializedName("medicationFrequency")
    val medicationFrequency: String,

    @SerializedName("medicationDuration")
    val medicationDuration: String,

    @SerializedName("dateOfIssue")
    val dateOfIssue: Date,

    @SerializedName("pharmacyInformation")
    val pharmacyInformation: String
)