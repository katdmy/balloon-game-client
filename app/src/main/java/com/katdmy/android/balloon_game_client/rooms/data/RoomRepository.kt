package com.katdmy.android.balloon_game_client.rooms.data

import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.rooms.data.models.ParticipantResponse
import com.katdmy.android.balloon_game_client.rooms.data.models.RoomResponse
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.common.retrofit.RoomApi
import com.katdmy.android.balloon_game_client.rooms.domain.IRoomRepository

class RoomRepository(
    private val roomApi: RoomApi,
    private val modelsMapper: ModelsMapper
) : IRoomRepository {

    override suspend fun createUser(username: String): String =
        roomApi.createUser("username\": \"$username")

    override suspend fun getRooms(): List<RoomsPlayers> =
        modelsMapper.fromApiToLocal(roomApi.getRooms())

    override suspend fun createPlayroom(playroomName: String): String =
        roomApi.createPlayroom("\"name\" $playroomName")

    override suspend fun confirmGame(roomId: String) =
        roomApi.confirmGame(roomId)

}
