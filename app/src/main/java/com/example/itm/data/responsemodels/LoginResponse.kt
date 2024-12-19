package com.example.itm.data.responsemodels

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("message")
    val message: String,
    val code: Int
)
