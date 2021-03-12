package com.katdmy.android.balloon_game_client

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.katdmy.android.balloon_game_client.utils.CustomSnivel
import com.sdsmdg.harjot.vectormaster.VectorMasterView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Меняем цвет пуза у хрюшки
        val mrPeppe = findViewById<VectorMasterView>(R.id.vPeppe)
        val peppesPaunch = mrPeppe.getPathModelByName("paunch")
        peppesPaunch.fillColor = Color.parseColor("#BB0000")

        val snivel = findViewById<CustomSnivel>(R.id.vSnivel)

        val item = findViewById<ConstraintLayout>(R.id.vPeppeWithSnivle)

        item.setOnClickListener {
            val random = (0..100).random()
            if (random in 10..90) {
                snivel.increaseSnivel()
            } else {
                snivel.clearSnivel()
            }
        }
    }
}