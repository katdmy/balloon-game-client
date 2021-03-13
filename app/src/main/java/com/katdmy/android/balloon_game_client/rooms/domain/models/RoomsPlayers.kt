package com.katdmy.android.balloon_game_client.rooms.domain.models

data class RoomsPlayers(
    val id: String,
    val name: String,
    val isRoom: Boolean,
    val isLast: Boolean = false
)