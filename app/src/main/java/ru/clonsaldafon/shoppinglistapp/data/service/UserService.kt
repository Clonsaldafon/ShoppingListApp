package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.POST
import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface UserService {

    @POST("")
    suspend fun signup(request: AuthRequest): Response<User>

    @POST("")
    suspend fun login(request: AuthRequest): Response<User>

}