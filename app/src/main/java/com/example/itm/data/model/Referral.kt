package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Referral(
    @SerializedName("referralId")
    val referralId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("medicalProfessionalBsn")
    val medicalProfessionalBsn: Int,

    @SerializedName("referredTo")
    val referredTo: Int,

    @SerializedName("reasonForReferral")
    val reasonForReferral: String,

    @SerializedName("dateOfReferral")
    val dateOfReferral: Date,

    @SerializedName("referralStatus")
    val referralStatus: ReferralStatus
)