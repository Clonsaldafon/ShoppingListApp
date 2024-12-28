package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.POST
import ru.clonsaldafon.shoppinglistapp.data.model.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface UserService {

    @POST("")
    suspend fun signup(request: SignUpRequest): Response<User>

}