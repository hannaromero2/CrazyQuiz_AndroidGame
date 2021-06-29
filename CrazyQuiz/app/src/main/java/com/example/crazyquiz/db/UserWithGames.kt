package com.example.crazyquiz.db

import androidx.room.Embedded
import androidx.room.Relation
import java.util.*

data class UserWithGames(
    @Embedded var user: Users,
    @Relation(
        entity = Game::class,
        parentColumn = "userId",
        entityColumn = "userId"
    )
    var games: List<GameWithSelectedQuestions>,
) {
    // obtiene los puntos totales entre todos los juegos
    val globalScore: Int
    get() {
        var total : Int = 0
        for (game in games) {
            if(!game.game.isActive && game.game.score > 0) {
                total += game.game.score
            }
        }
        return total;
    }

    // fecha del ultimo juego realizado
    val lastDate: Date?
    get() {
        if(games.isEmpty()) {
            return null
        }
        return games.last().game.date
    }

}