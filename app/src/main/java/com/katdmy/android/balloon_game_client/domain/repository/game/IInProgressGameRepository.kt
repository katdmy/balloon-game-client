package com.katdmy.android.balloon_game_client.domain.repository.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity.InProgressGameEntity
import kotlinx.coroutines.flow.Flow

interface IInProgressGameRepository {
    suspend fun subscribeRoomEvent(): Flow<InProgressGameEntity>
}