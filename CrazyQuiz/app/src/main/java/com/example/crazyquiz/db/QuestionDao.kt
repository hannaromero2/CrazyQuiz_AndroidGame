package com.example.crazyquiz.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Query("Select * from questions")
    fun getAll(): LiveData<List<Question>>

    @Query("Select * from questions where preguntaId = :id")
    fun getById(id: Int): Question


    @Update
    fun update(users: Users)

    @Insert
    fun insert(question: Question)
    //fun insert(people: List<Users>)

    @Delete
    fun delete(question: Question)
}