package com.example.crazyquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.crazyquiz.db.QuizRepository
import com.example.crazyquiz.db.Users

class EditActivity : AppCompatActivity() {

    private lateinit var button_editarperfil: Button
    private lateinit var button_regresar3: Button
    private lateinit var user: Users
    private lateinit var et_edit_name: EditText
    private lateinit var et_edit_email: EditText
    private lateinit var et_edit_password: EditText
    private lateinit var repository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ModelPreferencesManager.with(this.application)
        repository = QuizRepository(this.application)

        button_editarperfil = findViewById(R.id.button_editarperfil)
        button_regresar3 = findViewById(R.id.button_regresar3)
        et_edit_name = findViewById(R.id.et_edit_name)
        et_edit_email = findViewById(R.id.et_edit_email)
        et_edit_password = findViewById(R.id.et_edit_password)

        var savedUser = ModelPreferencesManager.get<Users>("USER")
        if(savedUser != null) {
            user = savedUser
            et_edit_name.setText(user.userName)
            et_edit_email.setText(user.userEmail)
            et_edit_password.setText(user.userPassword)
        }

        button_regresar3.setOnClickListener { View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        fun String.isEmailValid(): Boolean {
            return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
        }
        button_editarperfil.setOnClickListener { View ->

            user.userName = et_edit_name.text.toString()
            user.userEmail = et_edit_email.text.toString()
            user.userPassword = et_edit_password.text.toString()
            var error: Boolean = false;

            if(!user.userEmail.isEmailValid()) {
                Toast.makeText(
                    this,
                    "El formato de correo no es válido",
                    Toast.LENGTH_SHORT
                ).show()
                error = true
            }

            if(user.userPassword.length < 6) {
                Toast.makeText(
                    this,
                    "La contraseña debe ser mayor a 6 caracteres",
                    Toast.LENGTH_SHORT
                ).show()
                error = true
            }

            if(!error) {
                repository.updateUser(user)
                ModelPreferencesManager.put(user, "USER")
                Toast.makeText(
                    this,
                    "Se ha editado Correctamente",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}