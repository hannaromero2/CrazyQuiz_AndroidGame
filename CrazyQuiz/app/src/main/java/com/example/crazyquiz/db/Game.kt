package com.example.crazyquiz.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*

@Entity(
    tableName = "games",
    foreignKeys = [
        ForeignKey(
            entity = Users::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Game (
    @PrimaryKey(autoGenerate = true)
    val gameId : Int,
    var userId: Int,
    var isActive: Boolean,
    var score: Int,
    var date: Date,
    var currentQuestion: Int = 1,
    var numPistas: Int = 3
)