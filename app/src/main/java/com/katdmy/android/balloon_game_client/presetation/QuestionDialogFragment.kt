package com.katdmy.android.balloon_game_client.presetation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.katdmy.android.balloon_game_client.databinding.FragmentQuestionDialogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionDialogFragment : DialogFragment() {
    private var _binding: FragmentQuestionDialogBinding? = null
    private val binding get() = _binding!!
    private val questionViewModel: QuestionViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        questionViewModel.downloadQuestionModel()
        _binding = FragmentQuestionDialogBinding.inflate(inflater, container, false)

        setQuestionInfo()

        buttonsClickListener()

        return binding.root
    }

    override fun onStart() {
            super.onStart()
            val width = (resources.displayMetrics.widthPixels * 0.99).toInt()
            dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun buttonsClickListener() {
        binding.button1.setOnClickListener {
            questionViewModel.sendOption(binding.button1.text.toString())
            dismiss()
        }
        binding.button2.setOnClickListener {
            questionViewModel.sendOption(binding.button2.text.toString())
            dismiss()
        }
        binding.button3.setOnClickListener {
            questionViewModel.sendOption(binding.button3.text.toString())
            dismiss()
        }
        binding.button4.setOnClickListener {
            questionViewModel.sendOption(binding.button4.text.toString())
            dismiss()
        }
    }

    private fun setQuestionInfo() {

        questionViewModel.questionModel.observe(
            viewLifecycleOwner,
            {
                binding.textQuestion.text = it.question
                binding.button1.text = it.listAnswers[0]
                binding.button2.text = it.listAnswers[1]
                binding.button3.text = it.listAnswers[2]
                binding.button4.text = it.listAnswers[3]
            }
        )
    }
}