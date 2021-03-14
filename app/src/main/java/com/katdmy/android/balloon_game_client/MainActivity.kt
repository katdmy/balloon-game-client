package com.katdmy.android.balloon_game_client

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.katdmy.android.balloon_game_client.rooms.presentation.RoomFragment
import com.katdmy.android.balloon_game_client.utils.CustomSnivel

class MainActivity : AppCompatActivity() {

    private val roomFragment = RoomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTheme(R.style.Theme_Balloongameclient)

//        supportFragmentManager.beginTransaction()
//            .replace(R.id.activity_main, roomFragment)
//            .commit()


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


        //        _____________________________
        fun objectAnimator(
            duration: Long,
            view: View,
            property: String,
            vararg values: Float
        ): ObjectAnimator {

            val objectAnimator = ObjectAnimator.ofFloat(view, property, *values)
            objectAnimator.duration = duration
            return objectAnimator
        }

        val view = findViewById<ImageView>(R.id.imageView3)


        val alpha = objectAnimator(3500, view, "alpha", 1f, 0.8f)
        val translationX = objectAnimator(3500, view, "translationX", -580f, 0f)
        val scaleX = objectAnimator(3500, view, "scaleX", 0f, 1f)
        val scaleY = objectAnimator(3500, view, "scaleY", 0f, 1f)


        fun startAnimation() {
            alpha.start()
            translationX.start()
            scaleX.start()
            scaleY.start()
        }

        fun pauseAnimation() {
            alpha.pause()
            translationX.pause()
            scaleX.pause()
            scaleY.pause()
        }

        fun resumeAnimation() {
            alpha.resume()
            translationX.resume()
            scaleX.resume()
            scaleY.resume()
        }

        animatedPeppe.setOnClickListener {
            when {
                translationX.isPaused -> {
                    resumeAnimation()
                }
                translationX.isRunning -> {
                    pauseAnimation()

                }
                else -> {
                    startAnimation()
                }
            }
        }


    }

}