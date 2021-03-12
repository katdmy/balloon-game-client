package com.katdmy.android.balloon_game_client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers
import com.katdmy.android.balloon_game_client.rooms.presentation.RoomFragment

class MainActivity : AppCompatActivity(), RoomFragment.RoomOnClickListener {

    private val roomFragment = RoomFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_layout, roomFragment)
            .commit()
    }

    override fun launchGame(room: RoomsPlayers) {
        Toast.makeText(this, "${room.name} clicked!", Toast.LENGTH_SHORT).show()
        /*supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, movieDetails)
            .addToBackStack(null)
            .commit()*/
    }

}