package com.example.itm.data.responsemodels

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

    @SerializedName("message")
    val message: String,
    val code: Int
)
