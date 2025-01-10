package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.Member
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class GetMembersUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : GetMembersUseCase {

    override suspend fun invoke(groupId: Int): Result<List<Member>?> =
        repository.getMembers(groupId)

}