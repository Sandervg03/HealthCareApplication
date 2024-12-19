package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class DentalProcedure (
    @SerializedName("procedureId")
    val procedureId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("procedureName")
    val procedureName: String,

    @SerializedName("dateOfProcedure")
    val dateOfProcedure: Date,

    @SerializedName("cost")
    val cost: Double,

    @SerializedName("materialsUsed")
    val materialsUsed: String,

    @SerializedName("anesthesiaUsed")
    val anesthesiaUsed: Boolean
)
