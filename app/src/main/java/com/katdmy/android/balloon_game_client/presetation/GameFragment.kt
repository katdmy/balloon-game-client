package com.katdmy.android.balloon_game_client.presetation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.katdmy.android.balloon_game_client.databinding.FragmentGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private val questionViewModel: QuestionViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        questionViewModel.downloadQuestionModel(1)

        questionViewModel.timer.observe(
            viewLifecycleOwner,
            {
                binding.progressBar.progress = it.toInt()
                }
        )
        val questionDialogFragment = QuestionDialogFragment.newInstance(1, 30)
        fragmentManager?.let { questionDialogFragment.show(it, "questionDialog") }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onDetach() {
        super.onDetach()
    }



}
