package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.db.UserEntity
import ru.clonsaldafon.shoppinglistapp.data.model.ApiException
import ru.clonsaldafon.shoppinglistapp.data.model.Group
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

    override suspend fun signup(request: SignUpRequest, remember: Boolean): Result<TokenResponse?> {
        kotlin.runCatching {
            service.signup(request)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    if (dao.getUser().isNullOrEmpty())
                        dao.insertUser(
                            UserEntity(
                                login = if (remember) request.username else null,
                                password = if (remember) request.password else null,
                                accessToken = it.body()?.accessToken,
                                refreshToken = it.body()?.refreshToken
                            )
                        )
                    else
                        dao.updateUser(
                            UserEntity(
                                login = if (remember) request.username else null,
                                password = if (remember) request.password else null,
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

    override suspend fun login(request: LogInRequest, remember: Boolean): Result<TokenResponse?> {
        kotlin.runCatching {
            service.login(request)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    if (dao.getUser().isNullOrEmpty())
                        dao.insertUser(
                            UserEntity(
                                login = if (remember) request.username else null,
                                password = if (remember) request.password else null,
                                accessToken = it.body()?.accessToken,
                                refreshToken = it.body()?.refreshToken
                            )
                        )
                    else
                        dao.updateUser(
                            UserEntity(
                                login = if (remember) request.username else null,
                                password = if (remember) request.password else null,
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

    override suspend fun login(): Result<TokenResponse?> {
        kotlin.runCatching {
            val user = dao.getUser()?.last()
            service.login(
                LogInRequest(
                    username = user?.login ?: "",
                    password = user?.password ?: ""
                )
            )
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    val user = dao.getUser()?.last()
                    dao.updateUser(
                        UserEntity(
                            login = user?.login,
                            password = user?.password,
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

    override suspend fun refresh(): Result<TokenResponse?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.refreshToken
            service.refresh(
                RefreshTokenRequest(
                    refreshToken = token ?: ""
                )
            )
        }.fold(
            onSuccess = {
                return if (it.isSuccessful) {
                    val user = dao.getUser()?.last()
                    dao.insertUser(
                        UserEntity(
                            login = user?.login,
                            password = user?.password,
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

    override suspend fun getGroups(): Result<List<Group>?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.getGroups("Bearer $token")
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(it.body())
                else
                    Result.failure(ApiException(it.message(), it.code()))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun exit(): Result<String?> {
        kotlin.runCatching {
            dao.insertUser(
                UserEntity(
                    login = null,
                    password = null,
                    accessToken = null,
                    refreshToken = null
                )
            )
        }.fold(
            onSuccess = {
                return Result.success("success")
            },
            onFailure = {
                return Result.failure(Exception("failure"))
            }
        )
    }

}