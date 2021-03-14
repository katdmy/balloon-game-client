package com.katdmy.android.balloon_game_client.presetation.di.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.data.game.EndGameRepository
import com.katdmy.android.balloon_game_client.data.game.InProgressGameRepository
import com.katdmy.android.balloon_game_client.data.game.StartGameRepository
import com.katdmy.android.balloon_game_client.data.game.dto.InflateGameRequest
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.rooms.domain.models.Player
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import com.katdmy.android.balloon_game_client.utils.SingleLiveEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel(
    val model: StartGameModel,
    private val startGameRepo: StartGameRepository,
    private val inProgressGameRepository: InProgressGameRepository,
    private val endGameRepository: EndGameRepository

) : ViewModel() {

    private val _gameViewModel: MutableLiveData<StartGameModel> = MutableLiveData(model)
    val gameViewModel
        get() = _gameViewModel

    private val _players: MutableLiveData<List<Player>> = MutableLiveData(model.players.map { Player(it.id, it.name, model.myId == it.id, 0.025) }.sortedBy { !it.isYourPerson })
    val players get() = _players

    val _goToWinnersScreen: SingleLiveEvent<String> = SingleLiveEvent()


    private val _mutableTimer = MutableLiveData<Long>()
    val timer: LiveData<Long> get() = _mutableTimer

    init {
        viewModelScope.launch {
            Log.d("ballon", "duration " + model.duration)
            val flowTimer = startGameRepo.timer(model.duration.toLong())
            flowTimer.collect {
                _mutableTimer.value = it
            }
        }
        viewModelScope.launch {
            endGameRepository.subscribeRoomEvent(model.roomId).collect { endGameEntity ->
                _goToWinnersScreen.value = _players.value?.find { player -> player.id == endGameEntity.winnerId }!!.name
            }
        }

        viewModelScope.launch {
            inProgressGameRepository.subscribeRoomEvent(model.roomId).collect { inprogress ->
                val player = _players.value?.find { player -> player.id == inprogress.userId }
                player?.let {
                    val pos = _players.value!!.indexOf(player)
                    val players = _players.value!!.toMutableList()
                    players[pos] = player.copy(progress = inprogress.size)
                    _players.value = players
                }
            }
        }
    }

    fun onInflateClick(userId: String, size: Double, currentPossition: Int) {
        viewModelScope.launch {
            val random = (0..100).random()
            val newsize = if(random <= model.chance) {
                size + 0.025
            } else {
                0.05
            }
            Log.d("testtest", "$newsize ------ ${model.chance}")
            inProgressGameRepository.send(InflateGameRequest(model.roomId, userId, newsize))
            val players = _players.value!!.toMutableList()
            players[currentPossition] = players[currentPossition].copy(progress = newsize)
            _players.value = players
        }
    }
}