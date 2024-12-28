package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface UserRepository {

    suspend fun signup(request: SignUpRequest): Result<User?>

}