package ru.clonsaldafon.shoppinglistapp.data.repository

import retrofit2.HttpException
import ru.clonsaldafon.shoppinglistapp.data.model.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User
import ru.clonsaldafon.shoppinglistapp.data.service.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    //private val service: UserService
) : UserRepository {

    override suspend fun signup(request: SignUpRequest): Result<User?> {
//        kotlin.runCatching {
//            service.signup(request)
//        }.fold(
//            onSuccess = {
//                return if (it.isSuccessful)
//                    Result.success(it.body())
//                else Result.failure(HttpException(it))
//            },
//            onFailure = {
//                return Result.failure(it)
//            }
//        )
        TODO("provide user service in network module")
    }

}