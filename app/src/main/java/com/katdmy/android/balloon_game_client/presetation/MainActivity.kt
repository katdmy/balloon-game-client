package com.katdmy.android.balloon_game_client.presetation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.katdmy.android.balloon_game_client.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        textView.setOnClickListener {
            QuestionDialogFragment().show(supportFragmentManager, "questionDialog")
        }
    }
}