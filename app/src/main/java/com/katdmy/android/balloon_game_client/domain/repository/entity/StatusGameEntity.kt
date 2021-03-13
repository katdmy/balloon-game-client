package com.katdmy.android.balloon_game_client.domain.repository.entity

sealed class StatusGameEntity() {
    data class StartGameEntity(
        val duration: Int,
        val chance: Int,
        val questionNumber: Int,
        val participantIds: List<String>
    ) : StatusGameEntity()
    data class InProgressGameEntity(val userId: String, val size: Double) : StatusGameEntity()
    data class EndGameEntity(val winnerId: String) : StatusGameEntity()
}