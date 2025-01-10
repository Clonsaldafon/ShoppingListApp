package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.model.Member
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.model.group.AddProductRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.ProductResponse

interface GroupRepository {

    suspend fun create(request: CreateGroupRequest): Result<CreateGroupResponse?>

    suspend fun join(request: JoinToGroupRequest): Result<GroupResponse?>

    suspend fun delete(groupId: Int): Result<GroupResponse?>

    suspend fun leave(groupId: Int): Result<GroupResponse?>

    suspend fun getProducts(groupId: Int): Result<List<Product>?>

    suspend fun getMembers(groupId: Int): Result<List<Member>?>

    suspend fun addProduct(groupId: String, request: AddProductRequest): Result<ProductResponse?>

}