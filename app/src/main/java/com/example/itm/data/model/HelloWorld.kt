package com.example.itm.data.model

import com.google.gson.annotations.SerializedName

data class HelloWorld(

    @SerializedName("message")
    val message: String
)