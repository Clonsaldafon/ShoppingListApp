package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User

interface LogInUseCase {

    suspend operator fun invoke(request: AuthRequest): Result<User?>

}