package ru.clonsaldafon.shoppinglistapp.data.repository

import retrofit2.HttpException
import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.db.UserEntity
import ru.clonsaldafon.shoppinglistapp.data.model.ApiException
import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.RefreshTokenRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.service.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val service: UserService,
    private val dao: UserDAO
) : UserRepository {

    override suspend fun signup(request: SignUpRequest): Result<TokenResponse?> {
        kotlin.runCatching {
            service.signup(request)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    dao.upsertUser(
                        UserEntity(
                            accessToken = it.body()?.accessToken,
                            refreshToken = it.body()?.refreshToken
                        )
                    )

                    Result.success(it.body())
                } else {
                    Result.failure(ApiException(it.message(), it.code()))
                }
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun login(request: LogInRequest): Result<TokenResponse?> {
        kotlin.runCatching {
            service.login(request)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    dao.upsertUser(
                        UserEntity(
                            accessToken = it.body()?.accessToken,
                            refreshToken = it.body()?.refreshToken
                        )
                    )

                    Result.success(it.body())
                } else {
                    Result.failure(HttpException(it))
                }
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun refresh(request: RefreshTokenRequest): Result<TokenResponse?> {
        TODO("Not yet implemented")
    }

}