package com.katdmy.android.balloon_game_client.presetation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.katdmy.android.balloon_game_client.databinding.FragmentQuestionDialogBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionDialogFragment : DialogFragment() {
    private var _binding: FragmentQuestionDialogBinding? = null
    private val binding get() = _binding!!

    private val questionViewModel: QuestionViewModel by viewModel()

    private var rightAnswer: Int? = null
    private var chance: Int? = null

    companion object {
        fun newInstance(rightAnswer: Int, chance: Int) = QuestionDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(RIGHT_ANSWER, rightAnswer)
                putInt(CHANCE, chance )
            }
        }

        private const val RIGHT_ANSWER = "ANSWER_KEY"
        private const val CHANCE = "CHANCE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rightAnswer = arguments?.getInt(RIGHT_ANSWER)
    chance = arguments?.getInt(CHANCE)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activateButtons()
        setQuestionInfo()
        buttonsClickListener()
    }

    override fun onStart() {
            super.onStart()
            val width = (resources.displayMetrics.widthPixels * 0.99).toInt()
            dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        questionViewModel.downloadQuestionModel(1)
    }

    private fun buttonsClickListener() {
        binding.button1.setOnClickListener {
            deactivateButtons()
            checkAnswer(0)
                  }

        binding.button2.setOnClickListener {
            deactivateButtons()
            checkAnswer(1)
        }

        binding.button3.setOnClickListener {
            deactivateButtons()
            checkAnswer(2)        }

        binding.button4.setOnClickListener {
            deactivateButtons()
            checkAnswer(3)
        }

        binding.button5.setOnClickListener {
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
    fun deactivateButtons(){
        binding.button1.isVisible = false
        binding.button2.isVisible = false
        binding.button3.isVisible = false
        binding.button4.isVisible = false
        binding.button5.isVisible = true
    }

    fun activateButtons(){
        binding.button1.isVisible = true
        binding.button2.isVisible = true
        binding.button3.isVisible = true
        binding.button4.isVisible = true
        binding.button5.isVisible = false
    }
    private fun checkAnswer(variant: Int){
        if (variant == rightAnswer){
            binding.textQuestion.text = "The answer is correct! Your chance $chance%"
        }
        else{
            binding.textQuestion.text = "The answer is not correct."
        }
    }
}