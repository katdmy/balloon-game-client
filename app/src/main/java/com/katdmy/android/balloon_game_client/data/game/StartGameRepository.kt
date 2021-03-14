package com.katdmy.android.balloon_game_client.data.game

import android.util.Log
import com.google.gson.Gson
import com.katdmy.android.balloon_game_client.data.game.dto.StartGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.*
import ua.naiksoftware.stomp.Stomp

class StartGameRepository(private val client: OkHttpClient) : IStartGameRepository {
    private val stompClient = Stomp.over(
        Stomp.ConnectionProvider.OKHTTP,
        "ws://192.168.1.58:8080/ws-endpoint/websocket"
    );

    init {
        stompClient.connect()
        stompClient.topic("/game/d4576b3b305e1df6f8ef4517ec2f9615/start/events")
            .subscribe({ topicMessage -> Log.d("ballon2", topicMessage.getPayload()) }, { Log.e("ballon2", it.message, it) })
    }


    override suspend fun subscribeRoomEvent(roomId: String): Flow<StatusGameEntity.StartGameEntity> {
        return flow {

        }
    }

    override suspend fun sendStartGameEvent(req: StartGameRequest) {
        stompClient.send("/app/game/start", Gson().toJson(StartGameRequest("d4576b3b305e1df6f8ef4517ec2f9615", req.userId)))
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