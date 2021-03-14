package com.katdmy.android.balloon_game_client.rooms.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.data.game.dto.StartGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import com.katdmy.android.balloon_game_client.utils.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RoomViewModel(
    private val handle: SavedStateHandle,
    private val repo: RoomRepository,
    private val sharedPreferences: SharedPreferences,
    private val startRepo: IStartGameRepository
) : ViewModel() {

    private val _mutableRoomResponse = MutableLiveData<List<RoomsPlayers>>()
    val roomResponse get() = _mutableRoomResponse

    private val _startGameEvent: SingleLiveEvent<StartGameModel> = SingleLiveEvent()
    val startGameEvent
        get() = _startGameEvent

    private lateinit var userId: String
    private lateinit var currentRoomId: String

    fun getRooms() {
        viewModelScope.launch {
            _mutableRoomResponse.value = repo.getRooms()
        }
    }

    fun checkLogin(): LoginState {
        return if (sharedPreferences.contains(LOGIN_NAME)) {
            val username =
                sharedPreferences.getString(LOGIN_NAME, "defaultUsername") ?: "defaultUsername"
            userId =
                sharedPreferences.getString(LOGIN_ID, "0") ?: "0"
            LoginState.True(username, userId)
        } else
            LoginState.False
    }

    fun login(username: String) {
        val editor = sharedPreferences.edit()
        viewModelScope.launch {
            userId = repo.createUser(username)

            editor.putString(LOGIN_ID, userId)
            editor.putString(LOGIN_NAME, username)
            editor.apply()
        }
    }

    fun createPlayroom(playroomName: String) {
        viewModelScope.launch {
            currentRoomId = repo.createPlayroom(playroomName, userId)
            _mutableRoomResponse.value = repo.getRooms()
        }

    }

    fun joinRoom(roomId: String) {
        viewModelScope.launch {
            repo.joinRoom(userId, roomId)
            _mutableRoomResponse.value = repo.getRooms()
            currentRoomId = roomId

            playGame()
        }
    }

    fun playGame() {
        viewModelScope.launch {
            Log.d("testtest", "createPlayroom")
            startRepo.subscribeRoomEvent(currentRoomId).collect { entity ->
                _startGameEvent.value = StartGameModel(
                    duration = entity.duration,
                    chance = entity.chance,
                    questionNumber = entity.questionNumber,
                    players = _mutableRoomResponse.value!!.filter { it.roomId == currentRoomId },
                    roomId = entity.roomId,
                    myId = userId
                )
            }
        }
        viewModelScope.launch {
            startRepo.sendStartGameEvent(StartGameRequest(currentRoomId, userId))
        }
    }

    companion object {
        val LOGIN_ID = "loginId"
        val LOGIN_NAME = "loginName"
    }
}

