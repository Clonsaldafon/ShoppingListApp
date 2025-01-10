package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class CreateGroupUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : CreateGroupUseCase {

    override suspend fun invoke(request: CreateGroupRequest): Result<CreateGroupResponse?> =
        repository.create(request)

}