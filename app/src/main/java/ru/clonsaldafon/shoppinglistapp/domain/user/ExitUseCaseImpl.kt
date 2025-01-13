package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class ExitUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : ExitUseCase {

    override suspend fun invoke(): Result<String?> =
        repository.exit()

}