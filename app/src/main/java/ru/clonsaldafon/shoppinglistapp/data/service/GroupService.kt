package ru.clonsaldafon.shoppinglistapp.data.service

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.CreateGroupResponse
import ru.clonsaldafon.shoppinglistapp.data.model.group.JoinToGroupRequest
import ru.clonsaldafon.shoppinglistapp.data.model.group.GroupResponse

interface GroupService {

    @POST("/groups")
    suspend fun create(
        @Header("Authorization") token: String,
        request: CreateGroupRequest
    ): Response<CreateGroupResponse>

    @POST("/groups/join")
    suspend fun join(
        @Header("Authorization") token: String,
        request: JoinToGroupRequest
    ): Response<GroupResponse>

    @DELETE("/groups/{group_id}")
    suspend fun delete(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<GroupResponse>

    @DELETE("/groups/{group_id}/leave")
    suspend fun leave(
        @Header("Authorization") token: String,
        @Path("group_id") groupId: Int
    ): Response<GroupResponse>

}