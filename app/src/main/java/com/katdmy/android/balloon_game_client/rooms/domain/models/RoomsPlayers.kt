package com.katdmy.android.balloon_game_client.rooms.domain.models

data class RoomsPlayers(
    val id: String,
    val name: String,
    val roomId: String,
    val isRoom: Boolean,
    val roomOwnerId: String,
    val isLast: Boolean = false,
    var isChecked: Boolean = false
)