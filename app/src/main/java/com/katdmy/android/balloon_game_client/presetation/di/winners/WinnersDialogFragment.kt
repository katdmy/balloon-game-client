package com.katdmy.android.balloon_game_client.presetation.di.winners

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.katdmy.android.balloon_game_client.databinding.FragmentWinnersDialogBinding

/**
 * TODO: Зачем нужен этот класс?
 */
class WinnersDialogFragment: DialogFragment() {
    private var _binding: FragmentWinnersDialogBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance(winnerText: String) = WinnersDialogFragment().apply {
            arguments = Bundle().apply {
                putString(WINNER_TEXT, winnerText)
            }
        }

        private const val WINNER_TEXT = "WINNER_TEXT"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinnersDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.winnersText.text = requireArguments().getString(WINNER_TEXT)
        buttonsClickListener()
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.99).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun buttonsClickListener() {
        binding.winnersText.setOnClickListener {
            dismiss()
        }
    }
}