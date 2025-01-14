package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface LogInUseCase {

    suspend operator fun invoke(request: LogInRequest): Result<TokenResponse?>

}