package com.katdmy.android.balloon_game_client.presetation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.katdmy.android.balloon_game_client.databinding.HelpFragmentBinding

class HelpFragment : Fragment() {
    private var _binding: HelpFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HelpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

}