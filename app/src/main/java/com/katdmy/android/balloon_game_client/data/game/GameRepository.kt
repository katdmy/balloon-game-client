package com.katdmy.android.balloon_game_client.data.game

import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GameRepository : IStartGameRepository {
    override suspend fun subscribeRoomEvent(): Flow<StatusGameEntity.StartGameEntity> {
        TODO("Not yet implemented")
    }

    fun timer(timeMs: Long): Flow<Long> = flow {

        val time = timeMs / 1000
        for (i in time downTo 0) {
            delay(1000)
            emit(((i.toDouble() / time) * 100).toLong())
        }
    }
}