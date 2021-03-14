package com.katdmy.android.balloon_game_client.rooms.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartGameModel(
    val duration: Long,
    val chance: Int,
    val questionNumber: Int,
    val players: List<RoomsPlayers>,
    val roomId: String,
    val myId: String
) : Parcelable