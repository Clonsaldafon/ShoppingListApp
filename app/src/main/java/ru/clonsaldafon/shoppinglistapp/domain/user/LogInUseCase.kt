package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface LogInUseCase {

    suspend operator fun invoke(request: LogInRequest, remember: Boolean): Result<TokenResponse?>

    suspend operator fun invoke(): Result<TokenResponse?>

}