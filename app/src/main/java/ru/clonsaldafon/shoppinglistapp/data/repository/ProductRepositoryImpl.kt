package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.model.ApiException
import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory
import ru.clonsaldafon.shoppinglistapp.data.service.ProductService
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val service: ProductService,
    private val dao: UserDAO
) : ProductRepository {

    override suspend fun getCategories(): Result<List<Category>?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.getCategories("Bearer $token")
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(it.body())
                else
                    Result.failure(ApiException(it.message(), it.code()))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

    override suspend fun getProducts(categoryId: Int): Result<List<ProductByCategory>?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.getProducts("Bearer $token", categoryId)
        }.fold(
            onSuccess = {
                return if (it.isSuccessful)
                    Result.success(it.body())
                else
                    Result.failure(ApiException(it.message(), it.code()))
            },
            onFailure = {
                return Result.failure(it)
            }
        )
    }

}