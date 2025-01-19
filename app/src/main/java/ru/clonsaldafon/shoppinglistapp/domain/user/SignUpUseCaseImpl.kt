package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.SignUpRequest
import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : SignUpUseCase {

    override suspend fun invoke(request: SignUpRequest, remember: Boolean): Result<TokenResponse?> =
        repository.signup(request, remember)

}