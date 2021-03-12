package com.katdmy.android.balloon_game_client.rooms.data.models

import kotlinx.serialization.Serializable

@Serializable
class RoomResponse(
    val id: String,
    val name: String,
    val participants: List<ParticipantResponse>
)