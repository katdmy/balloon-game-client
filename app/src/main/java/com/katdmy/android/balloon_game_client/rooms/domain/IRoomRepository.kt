package com.katdmy.android.balloon_game_client.rooms.domain

import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

interface IRoomRepository {

    suspend fun createUser(username: String): String

    suspend fun getRooms(): List<RoomsPlayers>

    suspend fun createPlayroom(playroomName: String): String

    suspend fun confirmGame(roomId: String)
}