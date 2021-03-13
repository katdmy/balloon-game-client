package com.katdmy.android.balloon_game_client.rooms.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.common.retrofit.RetrofitClient.roomApi
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RoomViewModel(
    private val handle: SavedStateHandle,
    private val repo: RoomRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _mutableRoomResponse = MutableLiveData<List<RoomsPlayers>>()
    val roomResponse get() = _mutableRoomResponse

    fun getRooms() {
        viewModelScope.launch {
            _mutableRoomResponse.value = repo.getRooms()
        }
    }

    fun checkLogin(): LoginState {
        return if (sharedPreferences.contains(LOGIN_NAME)) {
            val username =
                sharedPreferences.getString(LOGIN_NAME, "defaultUsername") ?: "defaultUsername"
            val userId =
                sharedPreferences.getString(LOGIN_ID, "defaultId") ?: "defaultId"
            LoginState.True(username, userId)
        } else
            LoginState.False
    }

    fun login(username: String) {
        val editor = sharedPreferences.edit()
        viewModelScope.launch {
            val userId = repo.createUser(username)

            editor.putString(LOGIN_ID, userId)
            editor.putString(LOGIN_NAME, username)
            editor.apply()
        }

    }

    fun createPlayroom(playroomName: String) {
        viewModelScope.launch {
            repo.createPlayroom(playroomName)
            _mutableRoomResponse.value = repo.getRooms()
        }
    }


    fun createUser(username: String) {
        viewModelScope.launch {
            repo.createUser(username)
        }
    }

    fun confirmGame(roomId: String) {
        viewModelScope.launch {
            repo.confirmGame(roomId)
        }
    }

    companion object {
        val LOGIN_ID = "loginId"
        val LOGIN_NAME = "loginName"
    }
}

