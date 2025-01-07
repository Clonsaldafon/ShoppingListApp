package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface SignUpUseCase {

    suspend operator fun invoke(request: SignUpRequest): Result<TokenResponse?>

}