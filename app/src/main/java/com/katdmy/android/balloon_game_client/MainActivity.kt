package com.katdmy.android.balloon_game_client

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.katdmy.android.balloon_game_client.presetation.QuestionDialogFragment
import com.katdmy.android.balloon_game_client.utils.CustomSnivel


private lateinit var rocketAnimation: AnimationDrawable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animatedPeppe = findViewById<ImageView>(R.id.vPeppe)

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

        val textView: TextView = findViewById(R.id.textView)
        textView.setOnClickListener {
            QuestionDialogFragment().show(supportFragmentManager, "questionDialog")
        }
    }
}