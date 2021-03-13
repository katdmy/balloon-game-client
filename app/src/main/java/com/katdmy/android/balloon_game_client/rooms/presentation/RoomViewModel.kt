package com.katdmy.android.balloon_game_client.rooms.presentation

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import kotlinx.coroutines.launch

class RoomViewModel(
    private val handle: SavedStateHandle,
    private val repo: RoomRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _mutableRoomResponse = MutableLiveData<List<RoomsPlayers>>()
    val roomResponse get() = _mutableRoomResponse

    fun getData() {
        viewModelScope.launch {
            _mutableRoomResponse.value = repo.getData()
        }
    }

    fun checkLogin(): LoginState {
        return if (sharedPreferences.contains("LOGIN_NAME")) {
            val username =
                sharedPreferences.getString(LOGIN_NAME, "defaultUsername") ?: "defaultUsername"
            LoginState.True(username)
        } else
            LoginState.False
    }

    fun login(username: String) {
        val editor = sharedPreferences.edit()
        editor.putString(LOGIN_NAME, username)
        editor.apply()
    }

    companion object {
        val LOGIN_NAME = "loginName"
    }
}

