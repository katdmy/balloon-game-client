package com.katdmy.android.balloon_game_client.rooms.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.common.retrofit.RetrofitClient.roomApi
import com.katdmy.android.balloon_game_client.domain.repository.game.IStartGameRepository
import com.katdmy.android.balloon_game_client.domain.repository.game.StartFakeRepository
import com.katdmy.android.balloon_game_client.presetation.di.game.GameFragment.Companion.START_GAME_DATA
import com.katdmy.android.balloon_game_client.presetation.di.game.GameViewModel
import com.katdmy.android.balloon_game_client.presetation.di.viewModel
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel

class ViewModelFactory(
    private val activity: FragmentActivity,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(activity, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = when (modelClass) {
        RoomViewModel::class.java -> {
            val applicationContext = activity.applicationContext
            val sharedPreferences =
                applicationContext.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
            RoomViewModel(
                startRepo = StartFakeRepository(),
                handle = handle,
                repo = RoomRepository(
                    roomApi,
                    ModelsMapper()
                ),
                sharedPreferences
            )
        }
        GameViewModel::class.java -> {
            GameViewModel(
                model = defaultArgs?.getParcelable(START_GAME_DATA)!!,
                handle = handle
            )
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}