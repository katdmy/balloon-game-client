package com.katdmy.android.balloon_game_client.common.retrofit

import com.katdmy.android.balloon_game_client.rooms.data.models.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RoomApi {

    @POST("user/create")
    suspend fun createUser(@Body username: UserCreateRequest): UserCreateResponse

    @POST("room/create")
    suspend fun createPlayroom(@Body playroomName: PlayroomCreateRequest): PlayroomCreateResponse

    @GET("room/list")
    suspend fun getRooms(): List<RoomResponse>

    @POST("room/join")
    suspend fun joinRoom(@Body joinToRoomRequest: JoinToRoomRequest)
}