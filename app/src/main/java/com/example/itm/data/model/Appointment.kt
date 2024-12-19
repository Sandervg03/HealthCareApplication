package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Appointment (

    @SerializedName("appointmentId")
    val appointmentId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("appointmentDateTime")
    val appointmentDateTime: Date,

    @SerializedName("clinicAddress")
    val clinicAddress: String,

    @SerializedName("reasonForVisit")
    val reasonForVisit: String,

    @SerializedName("notesOrObservations")
    val notesOrObservations: String,

    @SerializedName("status")
    val status: AppointmentStatus
)