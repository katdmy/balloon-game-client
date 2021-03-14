package com.katdmy.android.balloon_game_client.presetation.di.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.katdmy.android.balloon_game_client.databinding.FragmentGameBinding
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity
import com.katdmy.android.balloon_game_client.domain.repository.entity.StatusGameEntity.StartGameEntity
import com.katdmy.android.balloon_game_client.rooms.presentation.ViewModelFactory
import com.katdmy.android.balloon_game_client.presetation.QuestionDialogFragment
import com.katdmy.android.balloon_game_client.presetation.di.winners.WinnersDialogFragment
import com.katdmy.android.balloon_game_client.rooms.domain.models.StartGameModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GameViewModel by viewModel(parameters = {
        parametersOf(
            arguments?.getParcelable(
                START_GAME_DATA
            )
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)

        setUpRecycler()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.timer.observe(
            viewLifecycleOwner, Observer {
                binding.progressBar.progress = it.toInt()
            }
        )

        binding.question.setOnClickListener {
            val data = requireArguments().getParcelable<StartGameModel>(START_GAME_DATA)
            QuestionDialogFragment.newInstance(data!!.questionNumber, data!!.chance).show(childFragmentManager, "quest")
        }


        viewModel._goToWinnersScreen.observe(viewLifecycleOwner, {
            val dialog = WinnersDialogFragment.newInstance("User ${it} winner" )
            dialog.show(childFragmentManager, "win")
        })
    }

    private fun setUpRecycler() {
        binding.run {
            vGamers.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                val gamerAdapter = GamersAdapter { userId, size, currentPossition ->
                    viewModel.onInflateClick(userId, size, currentPossition)
                }

                viewModel.players.observe(
                    viewLifecycleOwner,
                    Observer { players -> gamerAdapter.setPlayers(players) })

                adapter = gamerAdapter
            }
        }
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

    companion object {
        const val START_GAME_DATA = "start_game_data"
    }


}
