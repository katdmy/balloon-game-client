package com.katdmy.android.balloon_game_client.presetation.di

import com.katdmy.android.balloon_game_client.data.QuestionModelRepository
import com.katdmy.android.balloon_game_client.data.game.GameRepository
import com.katdmy.android.balloon_game_client.presetation.QuestionViewModel
import com.katdmy.android.balloon_game_client.presetation.di.game.GameViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { QuestionModelRepository() }
    single { GameRepository() }
}

val viewModel = module {
    viewModel {
        QuestionViewModel(get())
    }

    viewModel { (model: StartGameModel) ->
        GameViewModel(
            model = model,
            gameRepo = get()
        )
    }
}

