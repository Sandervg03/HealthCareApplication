package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class DentalRecord (
    @SerializedName("recordId")
    val recordId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("dateOfRecord")
    val dateOfRecord: Date,

    @SerializedName("dentalDiagnosis")
    val dentalDiagnosis: String,

    @SerializedName("treatmentProvided")
    val treatmentProvided: String,

    @SerializedName("teethCleaned")
    val teethCleaned: Boolean,

    @SerializedName("xrayResults")
    val xrayResults: String,

    @SerializedName("nextDentalAppointment")
    val nextDentalAppointment: Date
)