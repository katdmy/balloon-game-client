package com.katdmy.android.balloon_game_client.rooms.presentation

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.common.retrofit.RetrofitClient.roomApi
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository

class ViewModelFactory(
    activity: FragmentActivity,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(activity, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = when (modelClass) {
        RoomViewModel::class.java -> {
            RoomViewModel(
                handle,
                RoomRepository(
                    roomApi,
                    ModelsMapper()
                )
            )
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}