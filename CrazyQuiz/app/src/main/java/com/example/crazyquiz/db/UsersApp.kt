package com.example.crazyquiz.db

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class UsersApp : Application() {
    val room = Room
        .databaseBuilder(this, AppDatabase::class.java, "Users")
        .allowMainThreadQueries()
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                /*db.execSQL("INSERT INTO themes(id, description) VALUES(0, 'Cine')")
                db.execSQL("INSERT INTO themes(id, description) VALUES(1, 'Física')")
                db.execSQL("INSERT INTO themes(id, description) VALUES(2, 'Historia')")
                db.execSQL("INSERT INTO themes(id, description) VALUES(3, 'Matemáticas')")
                db.execSQL("INSERT INTO themes(id, description) VALUES(4, 'Videojuegos')")
                db.execSQL("INSERT INTO themes(id, description) VALUES(5, 'Arte')")*/
            }
        }).build()


}



//video de youtube de como crear la bd : https://www.youtube.com/watch?v=7N8X4DPQlNY