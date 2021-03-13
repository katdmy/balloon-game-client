package com.katdmy.android.balloon_game_client.domain.repository.entity

data class RoomEntity(val roomId: String, val name: String, val participants: List<UserEntity>)