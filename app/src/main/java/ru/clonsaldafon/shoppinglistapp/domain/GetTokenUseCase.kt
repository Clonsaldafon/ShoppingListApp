package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse

interface GetTokenUseCase {

    suspend operator fun invoke(): Result<TokenResponse?>

}