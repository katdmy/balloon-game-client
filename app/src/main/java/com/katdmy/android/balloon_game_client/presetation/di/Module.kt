package com.katdmy.android.balloon_game_client.presetation.di

import com.katdmy.android.balloon_game_client.common.retrofit.RetrofitClient
import com.katdmy.android.balloon_game_client.data.QuestionModelRepository
import com.katdmy.android.balloon_game_client.data.game.EndGameRepository
import com.katdmy.android.balloon_game_client.data.game.InProgressGameRepository
import com.katdmy.android.balloon_game_client.data.game.StartGameRepository
import com.katdmy.android.balloon_game_client.presetation.QuestionViewModel
import com.katdmy.android.balloon_game_client.presetation.di.game.GameViewModel
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { QuestionModelRepository() }
    single { StartGameRepository(RetrofitClient.client) }
    single { InProgressGameRepository() }
    single { EndGameRepository() }

}

val viewModel = module {
    viewModel {
        QuestionViewModel(get())
    }

    viewModel { (model: StartGameModel) ->
        GameViewModel(
            model = model,
            startGameRepo = get(),
            inProgressGameRepository = get(),
            endGameRepository = get()
        )
    }
}

