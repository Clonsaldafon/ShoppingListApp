package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.RefreshTokenRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface UserRepository {

    suspend fun signup(request: SignUpRequest): Result<TokenResponse?>

    suspend fun login(request: LogInRequest): Result<TokenResponse?>

    suspend fun refresh(request: RefreshTokenRequest): Result<TokenResponse?>

}