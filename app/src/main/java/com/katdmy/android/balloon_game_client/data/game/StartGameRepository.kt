package com.katdmy.android.balloon_game_client.data.game

import android.util.Log
import com.google.gson.Gson
import com.katdmy.android.balloon_game_client.data.game.dto.StartGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asFlow
import okhttp3.*
import ua.naiksoftware.stomp.Stomp
import kotlin.coroutines.suspendCoroutine

class StartGameRepository(private val client: OkHttpClient) : IStartGameRepository {
    private val stompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://143.198.226.5/ws-endpoint/websocket"
    );

    init {
        stompClient.connect()
    }


    override suspend fun subscribeRoomEvent(roomId: String): Flow<StatusGameEntity.StartGameEntity> {
        return stompClient.topic("/game/$roomId/start/events")
            .map {
                Log.d("ballon2", "start/events " + it.payload)
                Gson().fromJson(it.payload, StatusGameEntity.StartGameEntity::class.java)
            }
            .asFlow()
    }

    override suspend fun sendStartGameEvent(req: StartGameRequest) {
        stompClient.send(
            "/app/game/start",
            Gson().toJson(req)
        )
            .subscribe({
                Log.d("ballon", "yeah!")
            }, {
                Log.e("ballon", it.message, it)
            })
    }

    fun timer(timeMs: Long): Flow<Long> = flow {

        val time = timeMs / 1000
        for (i in time downTo 0) {
            delay(1000)
            emit(((i.toDouble() / time) * 100).toLong())
        }
    }
}