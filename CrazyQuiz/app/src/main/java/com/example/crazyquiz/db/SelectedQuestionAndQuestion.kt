package com.example.crazyquiz.db

import androidx.room.Embedded
import androidx.room.Relation


data class SelectedQuestionAndQuestion(
    @Embedded val selectedQuestion: SelectedQuestion,
    @Relation(
        entity = Question::class,
        parentColumn = "questionId",
        entityColumn = "preguntaId"
    )
    val question: Question,
){
    fun isAnswered(): Boolean {
        return selectedQuestion.answer != null && selectedQuestion.answer > 0
    }

    fun isCorrect(): Boolean {
        if(
            (selectedQuestion.answer == 1 && selectedQuestion.answer1.equals(question.answer1)) ||
            (selectedQuestion.answer == 2 && selectedQuestion.answer2.equals(question.answer1)) ||
            (selectedQuestion.answer == 3 && selectedQuestion.answer3.equals(question.answer1)) ||
            (selectedQuestion.answer == 4 && selectedQuestion.answer4.equals(question.answer1))
        ) {
            return true
        }
        return false
        //return selectedQuestion.answer == question.correcta
    }
}