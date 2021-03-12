package com.katdmy.android.balloon_game_client.data

import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay

class QuestionModelRepository {
    suspend fun getQuestionData(): QuestionModelDto {

        delay(3000)
        return QuestionModelDto(
            question ="Какого цвета небо ?",
            correctAnswerPosition = 3,
            listAnswers = listOf("Синее", "Голубое", "Сине-голубое", "Сине-бело-голубое")
        )
    }
}