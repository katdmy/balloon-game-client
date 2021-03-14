package com.katdmy.android.balloon_game_client.data.game

import android.util.Log
import com.google.gson.Gson
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IEndGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import ua.naiksoftware.stomp.Stomp

/**
 * TODO: Зачем нужен этот класс?
 */
class EndGameRepository: IEndGameRepository {
    private val stompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://143.198.226.5/ws-endpoint/websocket"
    );

    init {
        stompClient.connect()
    }

    override suspend fun subscribeRoomEvent(roomId: String): Flow<StatusGameEntity.EndGameEntity> {
        return stompClient.topic("/game/$roomId/end/events").asFlow().map {
            Log.d("ballon", "end game" + it.payload)
            Gson().fromJson(it.payload, StatusGameEntity.EndGameEntity::class.java)
        }
    }
}