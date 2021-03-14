package com.katdmy.android.balloon_game_client.rooms.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

class RoomAdapter(
    private val roomOnClickListener: (RoomsPlayers) -> Unit
) : RecyclerView.Adapter<ListItemViewHolder>() {

    private var roomsPlayers = listOf<RoomsPlayers>()

    override fun getItemViewType(position: Int): Int {
        return if (roomsPlayers[position].isRoom) {
            VIEW_TYPE_ROOM
        } else {
            VIEW_TYPE_PLAYER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return when (viewType) {
            VIEW_TYPE_ROOM -> RoomViewHolder(
                LayoutInflater.from(
                    parent.context
                ).inflate(R.layout.listitem_room, parent, false)
            )
            VIEW_TYPE_PLAYER -> PlayerViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.listitem_player, parent, false)
            )
            else -> throw(IllegalStateException("Wrong viewHolder type!"))
        }
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        when (holder) {
            is RoomViewHolder -> {
                val room = roomsPlayers[position]
                holder.onBind(room)
                holder.itemView.setOnClickListener {
                    holder.showPlayButton(roomOnClickListener, room)

//                    roomOnClickListener(room)
                }
                roomsPlayers.find { it.roomId == room.id }?.also { _ ->
                    holder.showPlayButton(roomOnClickListener, room)
                }
            }
            is PlayerViewHolder -> {
                holder.onBind(roomsPlayers[position])
            }
        }
    }

    override fun getItemCount(): Int = roomsPlayers.size

    fun setData(newRoomsPlayers: List<RoomsPlayers>) {
        roomsPlayers = newRoomsPlayers
        notifyDataSetChanged()
    }
}

abstract class ListItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

private class RoomViewHolder(itemView: View) : ListItemViewHolder(itemView) {

    private val roomName: TextView = itemView.findViewById(R.id.tv_room_name)
    private val playButton: Button = itemView.findViewById(R.id.bt_play)

    fun onBind(room: RoomsPlayers) {
        roomName.text = room.name
    }

    fun showPlayButton(playOnClickListener: (RoomsPlayers) -> Unit, room: RoomsPlayers) {
        playButton.visibility = View.VISIBLE
        playButton.setOnClickListener {
            playOnClickListener.invoke(room)
        }
    }
}

private class PlayerViewHolder(itemView: View) : ListItemViewHolder(itemView) {

    private val playerName: TextView = itemView.findViewById(R.id.tv_player_name)

    fun onBind(player: RoomsPlayers) {
        playerName.text = player.name
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context

private const val VIEW_TYPE_ROOM = 0
private const val VIEW_TYPE_PLAYER = 1