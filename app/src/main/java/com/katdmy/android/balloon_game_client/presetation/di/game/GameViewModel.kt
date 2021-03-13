package com.katdmy.android.balloon_game_client.presetation.di.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.data.game.GameRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.Player
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class GameViewModel(
    model: StartGameModel,
    private val gameRepo: GameRepository
) : ViewModel() {

    private val playerList = listOf(
        Player(
            "123",
            "Nagibator666",
            false
        ),
        Player(
            "1234",
            "Nagibator123",
            false
        ),
        Player(
            "123321",
            "Nagibator66",
            true
        )
    ).sortedBy {
        !it.isYourPerson
    }

    private val _gameViewModel: MutableLiveData<StartGameModel> = MutableLiveData(model)
    val gameViewModel
        get() = _gameViewModel

    private val _players: MutableLiveData<List<Player>> = MutableLiveData(playerList)
    val players get() = _players


    private val _mutableTimer = MutableLiveData<Long>()
    val timer: LiveData<Long> get() = _mutableTimer

    init {
        viewModelScope.launch {
            val flowTimer = gameRepo.timer(model.duration.toLong())
            flowTimer.collect {
                _mutableTimer.value = it
            }
        }

    }
}