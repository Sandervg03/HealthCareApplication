package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Patient (
    @SerializedName("bsn")
    val bsn: Int,

    @SerializedName("allergies")
    val allergies: String,

    @SerializedName("medicalHistory")
    val medicalHistory: String,

    @SerializedName("bloodType")
    val bloodType: String,

    @SerializedName("height")
    val height: Int,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("currentMedications")
    val currentMedications: String,

    @SerializedName("smokingStatus")
    val smokingStatus: String,

    @SerializedName("alcoholConsumption")
    val alcoholConsumption: String,

    @SerializedName("drugsConsumption")
    val drugsConsumption: String,

    @SerializedName("exerciseRoutine")
    val exerciseRoutine: String,

    @SerializedName("primaryPhysician")
    val primaryPhysician: Int,

    @SerializedName("dateOfRegistration")
    val dateOfRegistration: Date,

    @SerializedName("familyHistory")
    val familyHistory: String
)