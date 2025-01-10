package ru.clonsaldafon.shoppinglistapp.domain.group

import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse

interface CreateGroupUseCase {

    suspend operator fun invoke(request: CreateGroupRequest): Result<CreateGroupResponse?>

}