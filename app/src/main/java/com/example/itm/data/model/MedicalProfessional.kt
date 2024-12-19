package com.example.itm.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MedicalProfessional(
    @SerializedName("bsn")
    val bsn: Int,

    @SerializedName("bigNumber")
    val bigNumber: Int,

    @SerializedName("yearsOfExperience")
    val yearsOfExperience: Int,

    @SerializedName("degreesAndCertifications")
    val degreesAndCertifications: String,

    @SerializedName("licenseExpiryDate")
    val licenseExpiryDate: Date,

    @SerializedName("languagesSpoken")
    val languagesSpoken: String,

    @SerializedName("workSchedule")
    val workSchedule: String,

    @SerializedName("consultationFee")
    val consultationFee: Int,

    @SerializedName("availabilityStatus")
    val availabilityStatus: AvailabilityStatus,

    @SerializedName("contactEmail")
    val contactEmail: String,

    @SerializedName("officePhoneNumber")
    val officePhoneNumber: String,

    @SerializedName("clinicAddress")
    val clinicAddress: String,

    @SerializedName("professionalReviewsAverage")
    val professionalReviewsAverage: Int,

    @SerializedName("specialties")
    val specialties: String,

    @SerializedName("affiliatedClinics")
    val affiliatedClinics: Int
)