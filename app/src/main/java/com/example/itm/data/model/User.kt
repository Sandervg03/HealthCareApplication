package com.example.itm.data.model

import android.util.Log
import com.google.gson.annotations.SerializedName
import java.util.Date
import kotlin.reflect.full.memberProperties

data class User(

    @SerializedName("bsn")
    val bsn: Int,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("dateOfBirth")
    val dateOfBirth: Date,

    @SerializedName("userName")
    val userName: String,

    @SerializedName("password")
    val password: String? = null,

    @SerializedName("role")
    val role: Roles,

    @SerializedName("emergencyContactName")
    val emergencyContactName: String,

    @SerializedName("emergencyContactPhoneNumber")
    val emergencyContactPhoneNumber: String,

    @SerializedName("profilePicture")
    val profilePicture: ByteArray,

    @SerializedName("accountCreationDate")
    val accountCreationDate: Date,

    @SerializedName("lastLogin")
    val lastLogin: Date,

    @SerializedName("phoneNumber")
    val phoneNumber: String,

    @SerializedName("address")
    val address: String,

    @SerializedName("countryOfResidence")
    val countryOfResidence: String,

    @SerializedName("insurancePolicy")
    val insurancePolicy: String,

    @SerializedName("maritalStatus")
    val maritalStatus: MaritalStatus
)

fun User.validateRegister() {
    val missingFields = this::class.java.kotlin.memberProperties
        .filter { property ->
            val value = property.call(this)
            value.toString().isEmpty()
        }

    if (missingFields.isNotEmpty()) {
        Log.e("Missing inputs", missingFields.toString())
        throw Exception("Missing inputs: $missingFields")
    }
}
