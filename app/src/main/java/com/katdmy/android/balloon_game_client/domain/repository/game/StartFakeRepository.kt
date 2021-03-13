package com.katdmy.android.balloon_game_client.domain.repository.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StartFakeRepository() : IStartGameRepository {
    override suspend fun subscribeRoomEvent(): Flow<StatusGameEntity.StartGameEntity> {
        return flow {
            delay(2000L)
            emit(
                StatusGameEntity.StartGameEntity(
                    duration = 50000,
                    chance = 40,
                    questionNumber = 4,
                    participantIds = listOf(
                        "123", "122", "321"
                    ),
                    roomId = "1"
                )
            )
        }
    }
}