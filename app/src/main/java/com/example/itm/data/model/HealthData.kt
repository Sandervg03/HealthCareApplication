package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HealthData(
    @SerializedName("dateId")
    val dataId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("dateOfCollection")
    val dateOfCollection: Date,

    @SerializedName("heartRate")
    val heartRate: Int,

    @SerializedName("bloodPressure")
    val bloodPressure: String,

    @SerializedName("cholesterolLevel")
    val cholesterolLevel: String,

    @SerializedName("bloodSugarLevel")
    val bloodSugarLevel: String,

    @SerializedName("bmi")
    val bmi: Double,

    @SerializedName("sleepPatterns")
    val sleepPatterns: String,

    @SerializedName("stepCount")
    val stepCount: Int,

    @SerializedName("caloricIntake")
    val caloricIntake: Int,

    @SerializedName("exerciseDuration")
    val exerciseDuration: Int,

    @SerializedName("mentalHealthAssessment")
    val mentalHealthAssessment: String,

    @SerializedName("dietaryHabits")
    val dietaryHabits: String,

    @SerializedName("smokingAlcoholDrugHistory")
    val smokingAlcoholDrugHistory: String,
)