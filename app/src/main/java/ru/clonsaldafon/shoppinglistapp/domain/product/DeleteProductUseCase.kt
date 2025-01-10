package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse

interface DeleteProductUseCase {

    suspend operator fun invoke(groupId: String, productId: String): Result<GroupResponse?>

}