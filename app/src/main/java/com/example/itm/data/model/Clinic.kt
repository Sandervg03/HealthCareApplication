package com.example.itm.data.model

import com.google.gson.annotations.SerializedName

data class Clinic (
    @SerializedName("clinicId")
    val clinicId: Int?,

    @SerializedName("clinicName")
    val clinicName: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("contactInformation")
    val contactInformation: String,

    @SerializedName("specialtiesOffered")
    val specialtiesOffered: String,
)