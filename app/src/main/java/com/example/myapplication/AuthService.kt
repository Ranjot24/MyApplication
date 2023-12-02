package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("logout")
    fun logout(): Call<Void> // Adjust the return type based on your API

    @POST("signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String) // Adjust the properties based on your API

data class SignUpRequest(val email: String, val password: String)
data class SignUpResponse(val success: Boolean) // Adjust the properties based on your API
