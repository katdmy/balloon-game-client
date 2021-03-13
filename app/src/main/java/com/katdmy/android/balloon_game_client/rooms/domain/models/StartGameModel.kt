package com.katdmy.android.balloon_game_client.rooms.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartGameModel(
    val duration: Int,
    val chance: Int,
    val questionNumber: Int,
    val participantIds: List<String>
) : Parcelable