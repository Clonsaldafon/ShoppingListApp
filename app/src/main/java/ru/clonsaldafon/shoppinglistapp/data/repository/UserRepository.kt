package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface UserRepository {

    suspend fun signup(request: AuthRequest): Result<User?>

    suspend fun login(request: AuthRequest): Result<User?>

}