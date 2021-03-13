package com.katdmy.android.balloon_game_client.presetation.di.game

import android.graphics.drawable.Animatable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.rooms.domain.models.Player
import com.katdmy.android.balloon_game_client.rooms.presentation.ListItemViewHolder
import com.katdmy.android.balloon_game_client.utils.CustomSnivel

class GamersAdapter : RecyclerView.Adapter<ListItemViewHolder>() {

    private var players = listOf<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return GamersViewHolder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.item_pig_with_snivel, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        (holder as GamersViewHolder).onBind(players[position])
        if (players[position].isYourPerson)
            holder.itemView.setOnClickListener {

                val animatedPeppe = holder.itemView.findViewById<ImageView>(R.id.vPeppe)

                val snivel = holder.itemView.findViewById<CustomSnivel>(R.id.vSnivel)

                val item = holder.itemView

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
    }

    fun setPlayers(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }
}

private class GamersViewHolder(itemView: View) : ListItemViewHolder(itemView) {
    private val nickname: TextView = itemView.findViewById(R.id.vNickname)

    fun onBind(player: Player) {
        nickname.text = player.name
    }
}
