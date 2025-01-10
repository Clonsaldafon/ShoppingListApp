package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.Group
import ru.clonsaldafon.shoppinglistapp.data.repository.UserRepository
import javax.inject.Inject

class GetGroupsUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : GetGroupsUseCase {

    override suspend fun invoke(): Result<List<Group>?> =
        repository.getGroups()

}