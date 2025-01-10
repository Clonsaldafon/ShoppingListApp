package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetTokenUseCase {

    override suspend fun invoke(): Result<TokenResponse?> =
        repository.refresh()

}