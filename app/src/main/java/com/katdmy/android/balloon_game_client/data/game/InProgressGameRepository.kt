package com.katdmy.android.balloon_game_client.data.game

import android.util.Log
import com.google.gson.Gson
import com.katdmy.android.balloon_game_client.data.game.dto.InflateGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IInProgressGameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import ua.naiksoftware.stomp.Stomp

/**
 * TODO: Зачем нужен этот класс?
 */
class InProgressGameRepository: IInProgressGameRepository {
    private val stompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://143.198.226.5/ws-endpoint/websocket"
    );

    init {
        stompClient.connect()
    }

    override suspend fun subscribeRoomEvent(roomId: String): Flow<StatusGameEntity.InProgressGameEntity> {
        return stompClient.topic("/game/$roomId/inflate/events").asFlow().map {
            Log.d("ballon", "inflate game" + it.payload)
                Gson().fromJson(it.payload, StatusGameEntity.InProgressGameEntity::class.java)
            }
    }

    override suspend fun send(req: InflateGameRequest) {
        stompClient.send("/app/game/inflate", Gson().toJson(req)).subscribe({
            Log.d("send inflate", "yeah!")
        }, {
            Log.e("send inflate", it.message, it)
        })
    }
}