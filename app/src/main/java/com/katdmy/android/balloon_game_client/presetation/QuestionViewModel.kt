package com.katdmy.android.balloon_game_client.presetation

import android.util.Log
import androidx.lifecycle.*
import com.katdmy.android.balloon_game_client.data.QuestionModelRepository
import com.katdmy.android.balloon_game_client.data.QuestionModelDto
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuestionViewModel(private val questionRepository: QuestionModelRepository) : ViewModel() {
    private val _mutableLoadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> get() = _mutableLoadingState
    private val _mutableQuestionModel = MutableLiveData<QuestionModelDto>()
    val questionModel: LiveData<QuestionModelDto> get() = _mutableQuestionModel

    private val _mutableTimer = MutableLiveData<Long>()
    val timer: LiveData<Long> get() = _mutableTimer

     fun downloadQuestionModel(numberQuestion: Int){

         viewModelScope.launch {
             try {
                 _mutableLoadingState.value = LoadingState.LOADING
                 val questionData = questionRepository.getQuestionData(numberQuestion)
                 _mutableQuestionModel.value = questionData
                 _mutableLoadingState.value = LoadingState.LOADED
//                 val flowTimer = questionRepository.timer(questionData.timeQuestion)
//                 flowTimer.collect { _mutableTimer.value = it
//
//                 }
             } catch (exception: Exception) {
                 _mutableLoadingState.value = LoadingState.error(exception.message)
             }
         }

    }

    fun sendOption(variantAnswer: String) {

    }

}
