package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class InsurancePlan(
    @SerializedName("insurancePlanId")
    val insurancePlanId: Int?,

    @SerializedName("insuranceCompany")
    val insuranceCompany: String,

    @SerializedName("planType")
    val planType: String,

    @SerializedName("coverageDetails")
    val coverageDetails: String,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("expirationDate")
    val expirationDate: Date,

    @SerializedName("premiumAmount")
    val premiumAmount: Int
)