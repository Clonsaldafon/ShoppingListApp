package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.clonsaldafon.shoppinglistapp.data.model.Category
import ru.clonsaldafon.shoppinglistapp.data.model.ProductByCategory

interface ProductService {

    @GET("products/categories")
    suspend fun getCategories(
        @Header("Authorization") token: String
    ): Response<List<Category>>

    @GET("products/{category_id}")
    suspend fun getProducts(
        @Header("Authorization") token: String,
        @Path("category_id") categoryId: Int
    ): Response<List<ProductByCategory>>

}