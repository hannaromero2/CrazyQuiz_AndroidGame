package com.example.crazyquiz.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.crazyquiz.db.Users

@Dao
interface UsersDao {

    @Query("Select * from Users")
     fun getAll(): LiveData<List<UserWithGames>>

    @Query("Select * from Users where userEmail = :email and userPassword = :password")
    fun getByEmailPassword(email: String, password: String) : LiveData<Users>

    @Query("Select * from Users where userId = :id")
    fun getById(id: Int): LiveData<Users>

    @Update
    fun update(users: Users)

    @Insert
    fun insert(people: Users)
   //fun insert(people: List<Users>)

    @Delete
    fun delete(users: Users)
}