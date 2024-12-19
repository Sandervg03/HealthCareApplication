package com.example.itm.data.responsemodels

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @SerializedName("isRegistered")
    val isRegistered: Boolean
)