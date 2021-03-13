package com.katdmy.android.balloon_game_client.presetation.di.game

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel

class GameViewModel(
    model: StartGameModel,
    private val handle: SavedStateHandle,
) : ViewModel() {

    private val _gameViewModel: MutableLiveData<StartGameModel> = MutableLiveData(model)
    val gameViewModel
        get() = _gameViewModel

}