package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory

interface ProductRepository {

    suspend fun getCategories(): Result<List<Category>?>

    suspend fun getProducts(categoryId: Int): Result<List<ProductByCategory>?>

}