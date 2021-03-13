package com.katdmy.android.balloon_game_client.data.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import kotlinx.coroutines.flow.Flow

class GameRepository: IStartGameRepository {
    override suspend fun subscribeRoomEvent(): Flow<StatusGameEntity.StartGameEntity> {
        TODO("Not yet implemented")
    }
}