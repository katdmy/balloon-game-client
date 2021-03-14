package com.katdmy.android.balloon_game_client.rooms.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.presetation.di.game.GameFragment
import com.katdmy.android.balloon_game_client.presetation.di.game.GameFragment.Companion.START_GAME_DATA
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

class RoomFragment : Fragment(R.layout.room_fragment) {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RoomAdapter
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
        swipeRefreshLayout = view.findViewById(R.id.swipe_container)
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
        roomViewModel.roomResponse.observe(viewLifecycleOwner, { data ->
            run {
                adapter.setData(data)
                swipeRefreshLayout.isRefreshing = false
            }
        })

        roomViewModel.startGameEvent.observe(viewLifecycleOwner, Observer {
            val gameFragment = GameFragment()
            val bundle = Bundle()
            bundle.putParcelable(START_GAME_DATA, it)
            gameFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_main, gameFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        })
    }

    private fun setUpAdapter() {
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = RoomAdapter(
            { room: RoomsPlayers -> roomClickListener(room) },
            { playClickListener() }
        )
        recycler.adapter = adapter

    }

    private fun setUpClickListeners() {
        createPlayroom.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_create_playroom, null)

            val dialog = AlertDialog.Builder(requireContext())
                .setView(dialogView)
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
        swipeRefreshLayout.setOnRefreshListener {
            roomViewModel.getRooms()
        }
    }


    //creating method to make it look simpler
    private fun roomClickListener(room: RoomsPlayers) {
        val roomId = if (room.isRoom) room.id else room.roomId
        roomViewModel.joinRoom(roomId)
    }

    private fun playClickListener() {
        roomViewModel.playGame()
    }
}