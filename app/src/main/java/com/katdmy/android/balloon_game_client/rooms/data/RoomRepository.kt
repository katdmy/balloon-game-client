package com.katdmy.android.balloon_game_client.rooms.data

import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.common.retrofit.RoomApi
import com.katdmy.android.balloon_game_client.rooms.data.models.JoinToRoomRequest
import com.katdmy.android.balloon_game_client.rooms.data.models.PlayroomCreateRequest
import com.katdmy.android.balloon_game_client.rooms.data.models.UserCreateRequest
import com.katdmy.android.balloon_game_client.rooms.domain.IRoomRepository

class RoomRepository(
    private val roomApi: RoomApi,
    private val modelsMapper: ModelsMapper
) : IRoomRepository {

    override suspend fun createUser(username: String): String =
        roomApi.createUser(UserCreateRequest(username)).id

    override suspend fun getRooms(): List<RoomsPlayers> =
        modelsMapper.fromApiToLocal(roomApi.getRooms())

    override suspend fun createPlayroom(playroomName: String, userId: String): String =
        roomApi.createPlayroom(PlayroomCreateRequest(playroomName, userId)).id

    override suspend fun joinRoom(userId: String, roomId: String) {
        roomApi.joinRoom(JoinToRoomRequest(userId = userId, roomId = roomId))
    }
}
