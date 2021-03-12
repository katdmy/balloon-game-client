package com.katdmy.android.balloon_game_client.rooms.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import kotlinx.coroutines.launch

class RoomViewModel(
    private val handle: SavedStateHandle,
    private val repo: RoomRepository
) : ViewModel() {

    private val _mutableRoomResponse = MutableLiveData<List<RoomsPlayers>>()
    val roomResponse get() = _mutableRoomResponse

    fun getData() {
        viewModelScope.launch {
            _mutableRoomResponse.value = repo.getData()
        }
    }
}