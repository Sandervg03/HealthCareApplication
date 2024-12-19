package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Invoice(
    @SerializedName("invoiceId")
    val invoiceId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("dateIssued")
    val dateIssued: Date,

    @SerializedName("servicesRendered")
    val servicesRendered: String,

    @SerializedName("totalAmount")
    val totalAmount: Double,

    @SerializedName("paymentStatus")
    val paymentStatus: Boolean,

    @SerializedName("paymentMethod")
    val paymentMethod: String
)