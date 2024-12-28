package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface SignUpUseCase {

    suspend operator fun invoke(request: SignUpRequest): Result<User?>

}