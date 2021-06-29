package com.example.crazyquiz.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Users(
    @PrimaryKey(autoGenerate = true)
    val userId: Int,
    var userName: String,
    var userEmail: String,
    var userPassword: String,
    var allThemes: Boolean,
    var harryPotter: Boolean,
    var catReptiles: Boolean,
    var culturaGen: Boolean ,
    var food: Boolean,
    var terror: Boolean,
    var arteGeo: Boolean,
    var numPreguntas: String,
    var dificultad: Int,
    var habilitarPistas: Boolean,
    var numPistas: Int
    )
