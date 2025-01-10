package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest

interface JoinToGroupUseCase {

    suspend operator fun invoke(request: JoinToGroupRequest): Result<GroupResponse?>

}