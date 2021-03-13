package com.katdmy.android.balloon_game_client.common.retrofit

import com.katdmy.android.balloon_game_client.rooms.data.models.RoomResponse
import com.katdmy.android.balloon_game_client.rooms.data.models.UserCreateRequest
import com.katdmy.android.balloon_game_client.rooms.data.models.UserCreateResponse
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RoomApi {

    @POST("user/create")
    suspend fun createUser(@Body username: UserCreateRequest): UserCreateResponse

    @POST("room/create")
    suspend fun createPlayroom(@Body playroomName: String): String

    @GET("room/list")
    suspend fun getRooms(): List<RoomResponse>

    @POST("game/confirm")
    suspend fun confirmGame(@Body roomId: String)
}