package com.frxhaikal_plg.ingrevia.data.remote.model.auth

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)