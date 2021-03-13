package com.katdmy.android.balloon_game_client.presetation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.katdmy.android.balloon_game_client.data.QuestionModelDto
import com.katdmy.android.balloon_game_client.data.QuestionModelRepository
import kotlinx.coroutines.launch

class QuestionViewModel(private val questionRepository: QuestionModelRepository) : ViewModel() {
    private val _mutableLoadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _mutableLoadingState
    private val _mutableQuestionModel = MutableLiveData<QuestionModelDto>()
    val questionModel: LiveData<QuestionModelDto> get() = _mutableQuestionModel


    fun downloadQuestionModel(numberQuestion: Int) {

        viewModelScope.launch {
            try {
                _mutableLoadingState.value = LoadingState.LOADING
                val questionData = questionRepository.getQuestionData(numberQuestion)
                _mutableQuestionModel.value = questionData
                _mutableLoadingState.value = LoadingState.LOADED
            } catch (exception: Exception) {
                _mutableLoadingState.value = LoadingState.error(exception.message)
            }
        }

    }

    fun sendOption(variantAnswer: String) {

    }

}
