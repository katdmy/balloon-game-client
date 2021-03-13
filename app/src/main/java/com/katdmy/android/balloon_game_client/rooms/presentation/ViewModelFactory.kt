package com.katdmy.android.balloon_game_client.rooms.presentation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.ModelsMapper
import com.katdmy.android.balloon_game_client.common.retrofit.RetrofitClient.roomApi
import com.katdmy.android.balloon_game_client.rooms.data.RoomRepository

class ViewModelFactory(
    private val activity: FragmentActivity,
    defaultArgs: Bundle? = null
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
                handle,
                RoomRepository(
                    roomApi,
                    ModelsMapper()
                ),
                sharedPreferences
            )
        }
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T
}