package com.katdmy.android.balloon_game_client.rooms.presentation

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
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
            hideDialog()
            showRoomsPlayers(username)
            dialog.dismiss()
        }
    }

    private fun hideDialog() {
        dialogBackground.visibility = View.GONE
    }

    private fun showRoomsPlayers(username: String) {
        setUpAdapter()
        setUpClickListener()

        roomViewModel.getData()
        roomViewModel.roomResponse.observe(viewLifecycleOwner, { data -> adapter.setData(data) })
    }

    private fun setUpAdapter() {
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = RoomAdapter { room: RoomsPlayers -> roomClickListener(room) }
        recycler.adapter = adapter
    }

    private fun setUpClickListener() {
        roomClickListener = context as RoomOnClickListener
    }


    //creating method to make it look simpler
    private fun roomClickListener(room: RoomsPlayers) {
        //viewModel.selectedMovie = movie
        roomClickListener.launchGame(room)
    }

    interface RoomOnClickListener {
        fun launchGame(room: RoomsPlayers)
    }
}