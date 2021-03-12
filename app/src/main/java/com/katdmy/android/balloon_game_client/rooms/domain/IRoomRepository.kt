package com.katdmy.android.balloon_game_client.rooms.domain

import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

interface IRoomRepository {

    suspend fun getData() : List<RoomsPlayers>
}