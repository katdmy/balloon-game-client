package com.katdmy.android.balloon_game_client.presetation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.presetation.di.game.GameFragment

class MainActivity : AppCompatActivity() {
    private val gameFragment = GameFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container_fragment, gameFragment)
                .commit()
        }
    }
}