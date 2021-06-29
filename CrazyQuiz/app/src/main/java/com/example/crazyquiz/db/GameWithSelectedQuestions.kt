package com.example.crazyquiz.db

import androidx.room.Embedded
import androidx.room.Relation


data class GameWithSelectedQuestions(
    @Embedded var game: Game,
    @Relation(
        entity = SelectedQuestion::class,
        parentColumn = "gameId",
        entityColumn = "gameId"
    )
    var selectedQuestions: List<SelectedQuestionAndQuestion>,
    @Relation(
        entity = Users::class,
        parentColumn = "userId",
        entityColumn = "userId"
    )
    var user: Users,
) {
    val pistasUsadas: Int
        get() {
            var total : Int = 0
            for (selectedQuestion in selectedQuestions) {
                total += selectedQuestion.selectedQuestion.pistasUsadas
            }
            return total;
        }
}