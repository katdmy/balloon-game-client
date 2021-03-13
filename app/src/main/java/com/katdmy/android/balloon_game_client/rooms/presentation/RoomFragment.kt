package com.katdmy.android.balloon_game_client.rooms.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

class RoomFragment : Fragment(R.layout.room_fragment) {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RoomAdapter
    lateinit var roomClickListener: RoomOnClickListener
    private lateinit var dialogBackground: ImageView
    private lateinit var playerName: TextView
    private lateinit var createPlayroom: Button
    private val roomViewModel: RoomViewModel by activityViewModels {
        ViewModelFactory(
            requireActivity()
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        when (val loginState = roomViewModel.checkLogin()) {
            is LoginState.False -> showLoginDialog()
            is LoginState.True -> showRoomsPlayers(loginState.username)
        }
    }


    private fun initViews(view: View) {
        recycler = view.findViewById(R.id.rooms_list)
        dialogBackground = view.findViewById(R.id.iv_dialog_background)
        playerName = view.findViewById(R.id.tv_player_name)
        createPlayroom = view.findViewById(R.id.bt_create_playroom)
    }

    private fun showLoginDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_login, null)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val usernameEditText: EditText = dialogView.findViewById(R.id.et_login_name)
        val loginButton: Button = dialogView.findViewById(R.id.bt_login)
        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            roomViewModel.login(username)
            showRoomsPlayers(username)
            dialog.dismiss()
        }
    }

    private fun showRoomsPlayers(username: String) {
        dialogBackground.visibility = View.GONE
        playerName.text = username
        setUpAdapter()
        setUpClickListeners()

        roomViewModel.getRooms()
        roomViewModel.roomResponse.observe(viewLifecycleOwner, { data -> adapter.setData(data) })
    }

    private fun setUpAdapter() {
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = RoomAdapter { room: RoomsPlayers -> roomClickListener(room) }
        recycler.adapter = adapter

    }

    private fun setUpClickListeners() {
        roomClickListener = context as RoomOnClickListener

        createPlayroom.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_create_playroom, null)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
                .setCancelable(false)
                .create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()

            val playroomNameEditText: EditText = dialogView.findViewById(R.id.et_playroom_name)
            val createPlayroomButton: Button = dialogView.findViewById(R.id.bt_create_game)
            createPlayroomButton.setOnClickListener {
                val playroomName = playroomNameEditText.text.toString()
                roomViewModel.createPlayroom(playroomName)
                dialog.dismiss()
            }
        }
    }


    //creating method to make it look simpler
    private fun roomClickListener(room: RoomsPlayers) {
        //roomClickListener.launchGame(room)
        val roomId = if (room.isRoom) room.id else room.roomOwnerId
        Toast.makeText(requireContext(), "Room $roomId is pressed.", Toast.LENGTH_SHORT).show()
    }

    interface RoomOnClickListener {
        fun launchGame(room: RoomsPlayers)
    }
}