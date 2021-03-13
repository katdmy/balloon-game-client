package com.katdmy.android.balloon_game_client

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.rooms.presentation.RoomFragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.katdmy.android.balloon_game_client.presetation.QuestionDialogFragment
import com.katdmy.android.balloon_game_client.utils.CustomSnivel

class MainActivity : AppCompatActivity(), RoomFragment.RoomOnClickListener {

    private val roomFragment = RoomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animatedPeppe = findViewById<ImageView>(R.id.vPeppe)
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main, roomFragment)
            .commit()

        val snivel = findViewById<CustomSnivel>(R.id.vSnivel)

        val item = findViewById<ConstraintLayout>(R.id.vPeppeWithSnivle)

        item.setOnClickListener {
            val drawable = animatedPeppe.drawable
            (drawable as Animatable).start()
            val random = (0..100).random()
            if (random in 10..90) {
                snivel.increaseSnivel()
            } else {
                snivel.clearSnivel()
            }
        }

    }

    override fun launchGame(room: RoomsPlayers) {
        Toast.makeText(this, "${room.name} clicked!", Toast.LENGTH_SHORT).show()
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, movieDetails)
            .addToBackStack(null)
            .commit()*/
    }

}