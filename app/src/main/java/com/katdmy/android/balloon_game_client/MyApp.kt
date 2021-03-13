package com.katdmy.android.balloon_game_client

import android.app.Application
import com.katdmy.android.balloon_game_client.presetation.di.appModule
import com.katdmy.android.balloon_game_client.presetation.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(
                appModule,
                viewModel
            ))
        }
    }
}
