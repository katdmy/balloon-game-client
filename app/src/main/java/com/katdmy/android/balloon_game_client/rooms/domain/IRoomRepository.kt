package com.katdmy.android.balloon_game_client.rooms.domain

import com.katdmy.android.balloon_game_client.domain.repository.entity.RoomEntity
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

interface IRoomRepository {

    suspend fun getData() : List<RoomsPlayers>

    suspend fun createRoom(roomId: String, userId: String): RoomEntity

    suspend fun joinRoom(roomId: String, userId: String)

    suspend fun createUser(username: String): String

    suspend fun getRooms(): List<RoomsPlayers>

    suspend fun createPlayroom(playroomName: String): String

    suspend fun confirmGame(roomId: String)
}