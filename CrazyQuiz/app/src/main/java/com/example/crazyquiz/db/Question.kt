package com.example.crazyquiz.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "questions")

data class Question(
    @PrimaryKey(autoGenerate = true) val preguntaId : Int,
    val pregunta: String,
    val correcta: Int,
    val answer1: String,
    val answer2: String,
    val answer3: String,
    val answer4: String,
    val categoria: Int
)

