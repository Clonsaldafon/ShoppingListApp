package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.UpdateProductRequest

interface UpdateProductUseCase {

    suspend operator fun invoke(
        groupId: String,
        productId: String,
        request: UpdateProductRequest
    ): Result<GroupResponse?>

}