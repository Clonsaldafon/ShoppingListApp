package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.Group
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.RefreshTokenRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface UserRepository {

    suspend fun signup(request: SignUpRequest): Result<TokenResponse?>

    suspend fun login(request: LogInRequest): Result<TokenResponse?>

    suspend fun refresh(): Result<TokenResponse?>

    suspend fun getGroups(): Result<List<Group>?>

    suspend fun exit(): Result<String?>

}