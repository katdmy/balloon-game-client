package com.katdmy.android.balloon_game_client.rooms.domain.models

import com.katdmy.android.balloon_game_client.rooms.data.models.RoomResponse

class ModelsMapper {
    fun fromApiToLocal(apiResponse: List<RoomResponse>): List<RoomsPlayers> {
        val roomsPlayers = mutableListOf<RoomsPlayers>()
        apiResponse.forEach { room ->
            roomsPlayers.add(
                RoomsPlayers(
                    id = room.id,
                    name = room.name,
                    isRoom = true,
                    roomId = "",
                    roomOwnerId = ""
                )
            )
            room.participants.forEach { player ->
                roomsPlayers.add(
                    RoomsPlayers(
                        id = player.id,
                        name = player.name,
                        isRoom = false,
                        roomId = room.id,
                        roomOwnerId = ""
                    )
                )
            }
        }
        return roomsPlayers
    }
}