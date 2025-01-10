package ru.clonsaldafon.shoppinglistapp.domain.product

import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.repository.GroupRepository
import javax.inject.Inject

class DeleteProductUseCaseImpl @Inject constructor(
    private val repository: GroupRepository
) : DeleteProductUseCase {

    override suspend fun invoke(groupId: String, productId: String): Result<GroupResponse?> =
        repository.deleteProduct(groupId, productId)

}