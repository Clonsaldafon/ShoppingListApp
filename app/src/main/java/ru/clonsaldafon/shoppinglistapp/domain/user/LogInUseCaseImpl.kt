package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.LogInRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : LogInUseCase {

    override suspend fun invoke(request: LogInRequest): Result<TokenResponse?> =
        repository.login(request)

}