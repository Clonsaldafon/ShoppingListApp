package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.RefreshTokenRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.model.user.UserResponse

interface UserService {

    @POST("auth/signup")
    suspend fun signup(@Body request: SignUpRequest): Response<TokenResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LogInRequest): Response<TokenResponse>

    @POST("auth/refresh")
    suspend fun refresh(@Body request: RefreshTokenRequest): Response<TokenResponse>

    @GET("auth/who")
    suspend fun who(
        @Header("Authorization") token: String
    ): Response<UserResponse>

}