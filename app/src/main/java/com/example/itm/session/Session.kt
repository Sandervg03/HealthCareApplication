package com.example.itm.session

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.itm.data.model.Roles
import com.example.itm.data.model.User

object Session {

    var user: User? by mutableStateOf(null)

    var role: Roles? by mutableStateOf(null)

    var selectedUser: User? by mutableStateOf(null)
}