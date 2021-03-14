package com.katdmy.android.balloon_game_client.data.game

import android.util.Log
import com.google.gson.Gson
import com.katdmy.android.balloon_game_client.data.game.dto.StartGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.logging.Logger

class StartGameRepository(private val client: OkHttpClient) : IStartGameRepository {

    override suspend fun subscribeRoomEvent(roomId: String): Flow<StatusGameEntity.StartGameEntity> {
        return flow {
//            val request: Request = Request.Builder()
//                .url("ws://localhost:8080/game/$roomId/start/events")
//                .build();
//            client.newWebSocket(request, object: WebSocketListener() {
//                override fun onMessage(webSocket: WebSocket, text: String) {
//                    super.onMessage(webSocket, text)
//                    Log.d("ballon", text)
//                }
//            })
        }
    }

    override suspend fun sendStartGameEvent(req: StartGameRequest) {
//        val request: Request = Request.Builder()
//            .url("ws://localhost:8080/app/game/start")
//            .build();
//
//        val ws = client.newWebSocket(request, object: WebSocketListener() {
//
//        })
//
//        ws.send(Gson().toJson(req))
    }

    fun timer(timeMs: Long): Flow<Long> = flow {

        val time = timeMs / 1000
        for (i in time downTo 0) {
            delay(1000)
            emit(((i.toDouble() / time) * 100).toLong())
        }
    }
}