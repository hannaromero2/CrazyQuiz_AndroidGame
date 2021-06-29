package com.example.crazyquiz.modelo

import com.example.crazyquiz.db.GameWithSelectedQuestions
import com.example.crazyquiz.db.UserWithGames

data class Puntuaciones (
    val nombre: String,
    val imagen: String,
    val user: UserWithGames
) {
}