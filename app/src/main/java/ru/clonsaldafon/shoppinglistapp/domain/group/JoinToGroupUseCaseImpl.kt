package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class JoinToGroupUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : JoinToGroupUseCase {

    override suspend fun invoke(request: JoinToGroupRequest): Result<GroupResponse?> =
        repository.join(request)

}