package com.example.crazyquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crazyquiz.db.QuizRepository
import com.example.crazyquiz.db.Users
import com.example.crazyquiz.ui.login.LoginActivity
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var button_juego: Button
    private lateinit var button_options: Button
    private lateinit var button_puntaje: Button
    private lateinit var button_memorama: Button
    private lateinit var user: Users
    var isOpen = false
    private lateinit var repository: QuizRepository

    private val model: GameModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ModelPreferencesManager.with(this.application)
        repository = QuizRepository(this.application)

        //Para poder mostrar la bd en Chrome.
        Stetho.initializeWithDefaults(this)
        val fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open)
        val fabClosed = AnimationUtils.loadAnimation(this, R.anim.fab_closed)
        val fabRClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_clockwise)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this, R.anim.rotate_anticlockwise)

        button_juego = findViewById(R.id.button_juego)
        button_options = findViewById(R.id.button_options)
        button_puntaje = findViewById(R.id.button_puntaje)
        button_memorama = findViewById(R.id.button_memorama)
        // obtiene los settings, si los encuentra los asigna a settings.
        val savedUser = ModelPreferencesManager.get<Users>("USER")
        if(savedUser != null) {
            user = savedUser
        }

        fab_button.setOnClickListener {
            if (isOpen) {
                edit_button.startAnimation(fabClosed)
                button_exit.startAnimation(fabClosed)
                button_delete.startAnimation(fabClosed)
                fab_button.startAnimation(fabRAntiClockwise)

                isOpen = false
            } else {
                edit_button.startAnimation(fabOpen)
                button_exit.startAnimation(fabOpen)
                button_delete.startAnimation(fabOpen)
                fab_button.startAnimation(fabRClockwise)

                edit_button.isClickable
                button_exit.isClickable
                button_delete.isClickable
                isOpen = true
            }
            edit_button.setOnClickListener { View ->
                val intent = Intent(this, EditActivity::class.java)
                startActivity(intent)
            }
            button_exit.setOnClickListener { View ->
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Desea cerrar sesión?")
                    .setCancelable(false)
                    .setPositiveButton("SI") { dialog, id ->
                        ModelPreferencesManager.delete("USER")
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    .setNegativeButton("NO") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
            button_delete.setOnClickListener { View ->
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Desea eliminar este perfil?")
                    .setCancelable(false)
                    .setPositiveButton("SI") { dialog, id ->
                        repository.deleteUser(user);
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    .setNegativeButton("NO") { dialog, id ->
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }
        }
        button_options.setOnClickListener { View ->
            val intent = Intent(this, OptionsActivity::class.java)
            startActivity(intent)
        }
        button_juego.setOnClickListener { View ->
            val intent = Intent(this, QuestionActivity::class.java)
            startActivity(intent)
        }
        button_memorama.setOnClickListener { View ->
            val intent = Intent(this, MemoramaActivity::class.java)
            startActivity(intent)
        }
        button_puntaje.setOnClickListener { View ->
            val intent = Intent(this, PuntuacionesPerfilActivity::class.java)
            startActivity(intent)
        }
    }
}

