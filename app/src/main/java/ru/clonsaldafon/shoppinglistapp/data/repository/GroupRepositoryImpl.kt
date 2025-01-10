package ru.clonsaldafon.shoppinglistapp.data.repository

import ru.clonsaldafon.shoppinglistapp.data.db.UserDAO
import ru.clonsaldafon.shoppinglistapp.data.model.ApiException
import ru.clonsaldafon.shoppinglistapp.data.model.Member
import ru.clonsaldafon.shoppinglistapp.data.model.Product
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.service.GroupService
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val service: GroupService,
    private val dao: UserDAO
) : GroupRepository {

    override suspend fun create(request: CreateGroupRequest): Result<CreateGroupResponse?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.create("Bearer $token", request)
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

    override suspend fun join(request: JoinToGroupRequest): Result<GroupResponse?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.join("Bearer $token", request)
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

    override suspend fun delete(groupId: Int): Result<GroupResponse?> {
        TODO("Not yet implemented")
    }

    override suspend fun leave(groupId: Int): Result<GroupResponse?> {
        TODO("Not yet implemented")
    }

    override suspend fun getProducts(groupId: Int): Result<List<Product>?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.getProducts("Bearer $token", groupId)
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

    override suspend fun getMembers(groupId: Int): Result<List<Member>?> {
        kotlin.runCatching {
            val user = dao.getUser()
            val token = user?.last()?.accessToken
            service.getMembers("Bearer $token", groupId)
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