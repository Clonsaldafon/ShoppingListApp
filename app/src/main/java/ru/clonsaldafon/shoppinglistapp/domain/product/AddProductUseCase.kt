package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.ProductResponse

interface AddProductUseCase {

    suspend operator fun invoke(
        groupId: String,
        request: AddProductRequest
    ): Result<ProductResponse?>

}