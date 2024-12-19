package com.example.itm.data.responsemodels

import com.example.itm.data.model.User
import com.google.gson.annotations.SerializedName

data class IsAuthResponse(

    @SerializedName("isAuth")
    val isAuth: Boolean,

    @SerializedName("user")
    val user: User?,

    val code: Int
)
