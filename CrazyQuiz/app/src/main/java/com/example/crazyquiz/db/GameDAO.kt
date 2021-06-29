package com.example.crazyquiz.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface GameDao {

    @Query("Select * from games")
    fun getAll(): LiveData<List<Game>>

    @Query("Select * from games where userId = :userId")
    fun getByUser(userId: Int): LiveData<List<GameWithSelectedQuestions>>

    @Query("Select * from games where userId = :userId and isActive order by gameId desc limit 1")
    fun getActiveByUser(userId: Int): LiveData<GameWithSelectedQuestions>

    @Query("Select * from games where date = :date")
    fun getByDate(date: Date): LiveData<List<Game>>

    @Query("Select * from games where gameId = :gameId")
    fun getById(gameId: Int): LiveData<Game>

    //@Query("Select selectedQuestions.* from games, selectedquestions where date = :date")
    //fun getWithSelectedQuestions(gameId: Int): Game

    @Query ("Select * from games where not isActive  order by score desc limit 5")
    fun getTopFive(): LiveData<List<GameWithSelectedQuestions>>

    @Update
    fun update(game: Game)

    @Insert
    fun insert(game: Game)
    //fun insert(people: List<Users>)

    @Delete
    fun delete(game: Game)
}