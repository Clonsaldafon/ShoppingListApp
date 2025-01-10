package ru.clonsaldafon.shoppinglistapp.domain.user

import ru.clonsaldafon.shoppinglistapp.data.model.Group

interface GetGroupsUseCase {

    suspend operator fun invoke(): Result<List<Group>?>

}