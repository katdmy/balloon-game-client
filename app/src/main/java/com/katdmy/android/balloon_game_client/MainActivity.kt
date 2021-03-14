package com.katdmy.android.balloon_game_client

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.rooms.presentation.RoomFragment
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.katdmy.android.balloon_game_client.utils.CustomSnivel

class MainActivity : AppCompatActivity() {

    private val roomFragment = RoomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTheme(R.style.Theme_Balloongameclient)

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main, roomFragment)
            .commit()

        val animatedPeppe = findViewById<ImageView>(R.id.vPeppe)

        val snivel = findViewById<CustomSnivel>(R.id.vSnivel)

        val item = findViewById<ConstraintLayout>(R.id.vPeppeWithSnivle)

//        item.setOnClickListener {
//            val drawable = animatedPeppe.drawable
//            (drawable as Animatable).start()
//            val random = (0..100).random()
//            if (random in 10..90) {
//                snivel.increaseSnivel()
//            } else {
//                snivel.clearSnivel()
//            }
//        }

    }

}