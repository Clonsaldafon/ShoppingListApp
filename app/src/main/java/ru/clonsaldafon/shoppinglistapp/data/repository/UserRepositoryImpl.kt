package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.service.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val dao: UserDAO
) : UserRepository {

    override suspend fun signup(request: SignUpRequest): Result<TokenResponse?> {
//        kotlin.runCatching {
//            service.signup(request)
//        }.fold(
//            onSuccess = {
//                return if (it.isSuccessful) {
//                    dao.upsertUser(
//                        UserEntity(
//                            accessToken = it.body()?.accessToken,
//                            refreshToken = it.body()?.refreshToken
//                        )
//                    )
//
//                    Result.success(it.body())
//                } else {
//                    Result.failure(HttpException(it))
//                }
//            },
//            onFailure = {
//                return Result.failure(it)
//            }
//        )
        TODO("provide user service in network module")
    }

    override suspend fun login(request: LogInRequest): Result<TokenResponse?> {
//        kotlin.runCatching {
//            service.login(request)
//        }.fold(
//            onSuccess = {
//                return if (it.isSuccessful) {
//                    dao.upsertUser(
//                        UserEntity(
//                            accessToken = it.body()?.accessToken,
//                            refreshToken = it.body()?.refreshToken
//                        )
//                    )
//
//                    Result.success(it.body())
//                } else {
//                    Result.failure(HttpException(it))
//                }
//            },
//            onFailure = {
//                return Result.failure(it)
//            }
//        )
        TODO("provide user service in network module")
    }

}