package com.katdmy.android.balloon_game_client.data.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IInProgressGameRepository
import kotlinx.coroutines.flow.Flow

/**
 * TODO: Зачем нужен этот класс?
 */
class InProgressGameRepository: IInProgressGameRepository {
    override suspend fun subscribeRoomEvent(): Flow<StatusGameEntity.InProgressGameEntity> {
        TODO("Not yet implemented")
    }
}