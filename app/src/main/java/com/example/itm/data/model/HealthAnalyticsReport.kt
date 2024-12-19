package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class HealthAnalyticsReport (
    @SerializedName("reportId")
    val reportId: Int?,

    @SerializedName("patientBsn")
    val patientBsn: Int,

    @SerializedName("generatedDate")
    val generatedDate: Date,

    @SerializedName("analysisSummary")
    val analysisSummary: String,

    @SerializedName("healthRiskPrediction")
    val healthRiskPrediction: String,

    @SerializedName("recommendations")
    val recommendations: String,
)