package com.katdmy.android.balloon_game_client.domain.repository.game

import com.katdmy.android.balloon_game_client.data.game.dto.StartGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity.StartGameEntity
import kotlinx.coroutines.flow.Flow

interface IStartGameRepository {

    suspend fun subscribeRoomEvent(roomId: String): Flow<StartGameEntity>

    suspend fun sendStartGameEvent(req: StartGameRequest);
}