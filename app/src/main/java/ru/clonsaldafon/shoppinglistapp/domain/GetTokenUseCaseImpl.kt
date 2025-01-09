package ru.clonsaldafon.shoppinglistapp.domain

import ru.clonsaldafon.shoppinglistapp.data.model.user.TokenResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class GetTokenUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetTokenUseCase {

    override suspend fun invoke(): Result<TokenResponse?> {
        TODO("Not yet implemented")
    }

}