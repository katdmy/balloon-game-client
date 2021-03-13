package com.katdmy.android.balloon_game_client.data

import kotlinx.serialization.Serializable

data class QuestionModelDto (
    val question: String,
    val listAnswers: List<String>,
    val correctAnswerPosition: Int,
//    val timeQuestion: Long
)