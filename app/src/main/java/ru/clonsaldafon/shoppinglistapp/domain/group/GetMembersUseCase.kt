package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.Member

interface GetMembersUseCase {

    suspend operator fun invoke(groupId: Int): Result<List<Member>?>

}