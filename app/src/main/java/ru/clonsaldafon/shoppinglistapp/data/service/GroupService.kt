package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.clonsaldafon.shoppinglistapp.data.model.Member
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.ProductResponse

interface GroupService {

    @POST("groups")
    suspend fun create(
        @Header("Authorization") token: String,
        @Body request: CreateGroupRequest
    ): Response<CreateGroupResponse>

    @POST("groups/join")
    suspend fun join(
        @Header("Authorization") token: String,
        @Body request: JoinToGroupRequest
    ): Response<GroupResponse>

    @DELETE("groups/{group_id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<GroupResponse>

    @DELETE("groups/{group_id}/leave")
    suspend fun leave(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<GroupResponse>

    @GET("groups/{group_id}/products")
    suspend fun getProducts(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<List<Product>>

    @GET("groups/{group_id}/members")
    suspend fun getMembers(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<List<Member>>

    @POST("groups/{group_id}/products")
    suspend fun addProduct(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: String,
        @Body request: AddProductRequest
    ): Response<ProductResponse?>

    @DELETE("groups/{group_id}/products/{product_id}")
    suspend fun deleteProduct(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: String,
        @Path("product_id") productId: String
    ): Response<GroupResponse?>

}