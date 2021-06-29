package com.example.crazyquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.crazyquiz.db.QuizRepository
import com.example.crazyquiz.db.Users
import kotlinx.android.synthetic.main.activity_options.*

class OptionsActivity : AppCompatActivity() {

    private lateinit var button_return : Button
    private lateinit var button_aply : Button
    private lateinit var checkBox_todos : CheckBox
    private lateinit var checkBox_hp: CheckBox
    private lateinit var checkBox_gatosyreptiles: CheckBox
    private lateinit var checkBox_comida: CheckBox
    private lateinit var checkBox_terror: CheckBox
    private lateinit var checkBox_culturageneral: CheckBox
    private lateinit var checkBox_arteygeografia: CheckBox
    private lateinit var spinner_numpreguntas: Spinner
    private lateinit var spinner_pistas: Spinner
    private lateinit var radioGroup:RadioGroup
    private lateinit var radioButton_alta: RadioButton
    private lateinit var radioButton_media: RadioButton
    private lateinit var radioButton_baja: RadioButton
    private lateinit var switch_pistas: Switch
    private lateinit var adapter1: ArrayAdapter<String>
    private lateinit var adapter2: ArrayAdapter<String>
    private lateinit var user: Users
    private lateinit var repository: QuizRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_options)
        ModelPreferencesManager.with(this.application)

        repository = QuizRepository(this.application)

        button_aply = findViewById(R.id. button_apply)
        button_return = findViewById(R.id. button_return)
        checkBox_todos = findViewById(R.id.checkBox_todos)
        checkBox_hp = findViewById(R.id.checkBox_hp)
        checkBox_gatosyreptiles = findViewById(R.id.checkBox_gatosyreptiles)
        checkBox_comida = findViewById(R.id.checkBox_comida)
        checkBox_terror = findViewById(R.id.checkBox_terror)
        checkBox_culturageneral = findViewById(R.id.checkBox_culturageneral)
        checkBox_arteygeografia = findViewById(R.id.checkBox_arteygeografia)
        spinner_numpreguntas = findViewById(R.id.spinner_numpreguntas)
        spinner_pistas = findViewById(R.id. spinner_pistas)
        radioGroup = findViewById(R.id. radioGroup)
        radioButton_alta = findViewById(R.id.radioButton_alta)
        radioButton_media = findViewById(R.id.radioButton_media)
        radioButton_baja = findViewById(R.id.radioButton_baja)
        switch_pistas = findViewById(R.id.switch_pistas)

        button_return.setOnClickListener{  View ->
            val intent = Intent(this,MainActivity::class.java)
            // aqui se debe enviar "settings" a "MainActivity"
          //  intent.putExtras("Settings", settings)
            startActivity(intent)
        }

        button_apply.setOnClickListener{  View ->
            saveSettings()
            saveData()
            val intent = Intent(this,MainActivity::class.java)


            // aqui se debe enviar "settings" a el nuevo activity de las preguntas
            startActivity(intent)

        }

        var spinner_info = arrayOf("5","6","7", "8", "9","10")
        adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner_info)
        spinner_numpreguntas.adapter = adapter1

        var spinner_info2 = arrayOf("1","2", "3")
        adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinner_info2)
        spinner_pistas.adapter = adapter2

        checkBox_todos.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_hp.isChecked = false
                checkBox_arteygeografia.isChecked = false
                checkBox_terror.isChecked = false
                checkBox_comida.isChecked = false
                checkBox_culturageneral.isChecked = false
                checkBox_gatosyreptiles.isChecked = false
            }
        }
        checkBox_hp.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }
        checkBox_gatosyreptiles.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }
        checkBox_culturageneral.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }
        checkBox_comida.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }
        checkBox_terror.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }
        checkBox_arteygeografia.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                checkBox_todos.isChecked = false
            }
        }

        radioGroup.setOnClickListener{  View ->
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        radioButton_alta.setOnClickListener{  View ->
            user.dificultad = 3
        }
        radioButton_media.setOnClickListener{  View ->
            user.dificultad = 2
        }
        radioButton_baja.setOnClickListener{  View ->
            user.dificultad = 1
        }
        switch_pistas.setOnClickListener{  View ->

        }

        //settings = Settings(true, false, false, false, false, false, false, "6", 2, false, "2")
        var savedUser = ModelPreferencesManager.get<Users>("USER")
        if(savedUser != null) {
            user = savedUser
        }
        loadSettings()
    }

//
    fun loadSettings() {
        checkBox_todos.isChecked = user.allThemes
        checkBox_hp.isChecked = user.harryPotter
        checkBox_gatosyreptiles.isChecked = user.catReptiles
        checkBox_culturageneral.isChecked = user.culturaGen
        checkBox_comida.isChecked = user.food
        checkBox_terror.isChecked = user.terror
        checkBox_arteygeografia.isChecked = user.arteGeo
        spinner_numpreguntas.setSelection(adapter1.getPosition(user.numPreguntas))
        spinner_pistas.setSelection(adapter2.getPosition(user.numPistas.toString()))
        switch_pistas.isChecked = user.habilitarPistas

        if(user.dificultad == 3) {
            radioButton_alta.isChecked = true;
        }
        if(user.dificultad == 2) {
            radioButton_media.isChecked = true;
        }
        if(user.dificultad == 1) {
            radioButton_baja.isChecked = true;
        }
    }

    fun saveSettings() {
        user.allThemes = checkBox_todos.isChecked
        user.harryPotter = checkBox_hp.isChecked
        user.catReptiles = checkBox_gatosyreptiles.isChecked
        user.catReptiles = checkBox_gatosyreptiles.isChecked
        user.culturaGen = checkBox_culturageneral.isChecked
        user.food = checkBox_comida.isChecked
        user.terror = checkBox_terror.isChecked
        user.arteGeo = checkBox_arteygeografia.isChecked
        user.numPreguntas = spinner_numpreguntas.selectedItem.toString()
        user.numPistas = spinner_pistas.selectedItem.toString().toInt()
        user.habilitarPistas = switch_pistas.isChecked
    }




    private fun saveData(){
        repository.updateUser(user)
        ModelPreferencesManager.put(user, "USER")
    }

}