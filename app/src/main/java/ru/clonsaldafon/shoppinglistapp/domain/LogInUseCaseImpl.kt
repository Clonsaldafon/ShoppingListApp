package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.AuthRequest
import ru.clonsaldafon.shoppinglistapp.data.model.User
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : LogInUseCase {

    override suspend fun invoke(request: AuthRequest): Result<User?> =
        repository.login(request)

}