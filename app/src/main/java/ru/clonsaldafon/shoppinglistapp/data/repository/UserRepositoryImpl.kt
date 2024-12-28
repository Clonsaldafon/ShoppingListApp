package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    //private val service: UserService
) : UserRepository {

    override suspend fun signup(request: AuthRequest): Result<User?> {
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

    override suspend fun login(request: AuthRequest): Result<User?> {
        TODO("Not yet implemented")
    }

}