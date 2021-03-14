package com.katdmy.android.balloon_game_client.data.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IEndGameRepository
import kotlinx.coroutines.flow.Flow

/**
 * TODO: Зачем нужен этот класс?
 */
class EndGameRepository: IEndGameRepository {
    override suspend fun subscribeRoomEvent(): Flow<StatusGameEntity.EndGameEntity> {
        TODO("Not yet implemented")
    }
}