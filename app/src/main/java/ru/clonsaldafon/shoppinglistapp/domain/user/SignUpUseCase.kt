package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface SignUpUseCase {

    suspend operator fun invoke(request: SignUpRequest): Result<TokenResponse?>

}