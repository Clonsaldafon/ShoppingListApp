package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : SignUpUseCase {

    override suspend fun invoke(request: AuthRequest): Result<User?> =
        repository.signup(request)

}