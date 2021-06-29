package com.example.crazyquiz.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SelectedQuestionDao {

    @Query("Select * from selectedQuestions")
    fun getAll(): LiveData<List<SelectedQuestion>>

    @Query("Select * from selectedQuestions where gameId = :gameId")
    fun getByGameId(gameId: Int): LiveData<List<SelectedQuestion>>

    @Update
    fun update(selectedQuestion: SelectedQuestion)

    @Insert
    fun insert(selectedQuestion: SelectedQuestion) : Long
    // fun insert(questions: List<SelectedQuestion>)

    @Delete
    fun delete(selectedQuestion: SelectedQuestion)


}