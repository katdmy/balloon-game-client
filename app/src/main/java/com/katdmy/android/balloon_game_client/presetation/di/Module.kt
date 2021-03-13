package com.katdmy.android.balloon_game_client.presetation.di

import com.katdmy.android.balloon_game_client.presetation.QuestionViewModel
import com.katdmy.android.balloon_game_client.data.QuestionModelRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { QuestionModelRepository() }
}

val viewModel = module {
    viewModel { QuestionViewModel(get()) }
     }

