package com.example.itm.data.model

import com.google.gson.annotations.SerializedName

data class PatientDashboard(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("nextAppointment")
    val nextAppointment: Int,

    @SerializedName("upcomingTests")
    val upcomingTests: Int,

    @SerializedName("pendingInvoices")
    val pendingInvoices: Int,

    @SerializedName("prescriptionRefills")
    val prescriptionRefills: Int,

    @SerializedName("healthSummary")
    val healthSummary: Int
)