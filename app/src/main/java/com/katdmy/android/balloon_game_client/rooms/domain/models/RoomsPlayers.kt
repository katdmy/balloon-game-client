package com.katdmy.android.balloon_game_client.rooms.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoomsPlayers(
    val id: String,
    val name: String,
    val roomId: String,
    var showPlayButton: Boolean = false,
    val isRoom: Boolean,
    val roomOwnerId: String,
    val isLast: Boolean = false,
    var isChecked: Boolean = false
) : Parcelable