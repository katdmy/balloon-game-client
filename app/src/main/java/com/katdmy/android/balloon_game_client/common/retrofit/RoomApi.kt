package com.katdmy.android.balloon_game_client.common.retrofit

import com.katdmy.android.balloon_game_client.rooms.data.models.RoomResponse
import retrofit2.http.GET

interface RoomApi {

    @GET("list")
    suspend fun getList(): List<RoomResponse>

}