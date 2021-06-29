package com.example.crazyquiz.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    tableName = "selectedQuestions",
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = ["gameId"],
            childColumns = ["gameId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class SelectedQuestion (
    @PrimaryKey(autoGenerate = true)
    var selectedQuestionId: Int,
    var answer : Int,
    var questionId: Int,

    var gameId : Int,
    var answer1: String = "",
    var answer2: String = "",
    var answer3: String = "",
    var answer4: String = "",
    var answer1Locked: Boolean = false,
    var answer2Locked: Boolean = false,
    var answer3Locked: Boolean = false,
    var answer4Locked: Boolean = false
) {
    val pistasUsadas: Int
    get() {
        var total: Int = 0
        if(answer1Locked) {
            total++
        }
        if(answer2Locked) {
            total++
        }
        if(answer3Locked) {
            total++
        }
        if(answer4Locked) {
            total++
        }
        return total
    }
}