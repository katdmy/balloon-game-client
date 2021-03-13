package com.katdmy.android.balloon_game_client.domain.repository.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity.EndGameEntity
import kotlinx.coroutines.flow.Flow

interface IEndGameRepository {
    suspend fun subscribeRoomEvent(): Flow<EndGameEntity>
}