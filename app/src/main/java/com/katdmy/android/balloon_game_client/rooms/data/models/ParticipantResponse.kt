package com.katdmy.android.balloon_game_client.rooms.data.models

import kotlinx.serialization.Serializable

@Serializable
data class ParticipantResponse(
    val id: String,
    val name: String
)