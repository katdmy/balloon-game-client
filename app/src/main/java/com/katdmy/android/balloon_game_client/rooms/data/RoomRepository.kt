package com.katdmy.android.balloon_game_client.rooms.data

import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.rooms.data.models.ParticipantResponse
import com.katdmy.android.balloon_game_client.rooms.data.models.RoomResponse
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.common.retrofit.RoomApi
import com.katdmy.android.balloon_game_client.domain.repository.entity.RoomEntity
import com.katdmy.android.balloon_game_client.rooms.domain.IRoomRepository

class RoomRepository(
    private val roomApi: RoomApi,
    private val modelsMapper: ModelsMapper
) : IRoomRepository {

    override suspend fun getData(): List<RoomsPlayers> =
        modelsMapper.fromApiToLocal(roomApi.getList())

    override suspend fun createRoom(roomId: String, userId: String): RoomEntity {
        TODO("Not yet implemented")
    }

    override suspend fun joinRoom(roomId: String, userId: String) {
        TODO("Not yet implemented")
    }
}
