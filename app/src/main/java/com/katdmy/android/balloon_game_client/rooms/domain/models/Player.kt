package com.katdmy.android.balloon_game_client.rooms.domain.models

data class Player(
    val id: String,
    val name: String,
    val isYourPerson: Boolean,
    var progress: Double
)