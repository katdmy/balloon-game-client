package com.katdmy.android.balloon_game_client.rooms.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.katdmy.android.balloon_game_client.R
import com.katdmy.android.balloon_game_client.rooms.domain.models.RoomsPlayers

class RoomFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private lateinit var adapter: RoomAdapter
    lateinit var roomClickListener: RoomOnClickListener
    private val roomViewModel: RoomViewModel by activityViewModels {
        ViewModelFactory(
            requireActivity()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.room_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpAdapter()
        setUpClickListener()

        roomViewModel.getData()
        roomViewModel.roomResponse.observe(viewLifecycleOwner, { data -> adapter.setData(data) } )
    }


    private fun initViews(view: View) {
        recycler = view.findViewById(R.id.rooms_list)
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