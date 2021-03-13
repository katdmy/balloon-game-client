package com.katdmy.android.balloon_game_client.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class QuestionModelRepository {
    suspend fun getQuestionData(numberQuestion: Int): QuestionModelDto {

        val listQuestionModel = listOf<QuestionModelDto>(
            QuestionModelDto(
                "What is always coming, but never arrives?",
                listOf<String>("Yesterday", "Tomorrow", "Old friend", "Salary"),
                1,
            ),

            QuestionModelDto(
                "What is it that lives if it is fed, and dies if you give it a drink?",
                listOf<String>("Computer", "Water", "Alcoholic", "Fire"),
                3
            ),


            QuestionModelDto(
                "What is it that if you have, you want to share me, and if you share, you do not have?",
                listOf<String>("Money", "A secret", "Soul", "Time"),
                1
            ),

            QuestionModelDto(
                "If you had only one match and entered a dark room containing an oil lamp, some kindling wood, and a newspaper, which would you light first?",
                listOf<String>("Kindling wood", "Newspaper", "Oil lamp", "The match"),
                3
            ),
            QuestionModelDto(
                "Uncle Bill’s farm had a terrible storm and all but seven sheep were killed. How many sheep are still alive?",
                listOf<String>("Seven", "Nine", "A hundred", "One thousand"),
                0
            ),

            QuestionModelDto(
                "What goes up and down, but always remains in the same place?",
                listOf<String>("Road", "Stairs", "The past", "Fat Lady"),
                0
            ),

            QuestionModelDto(
                "What is it that goes up, but never comes down?",
                listOf<String>("A tram", "A train", "Age", "tmpgrname team"),
                2
            )
        )
        return listQuestionModel[numberQuestion]
    }
}