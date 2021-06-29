package com.example.crazyquiz

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColorInt
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.crazyquiz.db.*
import kotlinx.android.synthetic.main.activity_question.*
import java.util.*
import kotlin.math.roundToInt

class QuestionActivity : AppCompatActivity() {

    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var preguntaTextView: TextView
    private lateinit var numPreguntaTextView: TextView
    private lateinit var Opcion1: Button
    private lateinit var Opcion2: Button
    private lateinit var Opcion3: Button
    private lateinit var Opcion4: Button
    private lateinit var AnswerCorrect: Button
    private lateinit var PuntuacionTotal: TextView
    private lateinit var numPistas: Button
    private lateinit var repository: QuizRepository
    private var alertShowed: Boolean = false

    private val model: GameModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        ModelPreferencesManager.with(this.application)
        repository = QuizRepository(this.application) //hace referencia al repositorio de la bd

        prevButton = findViewById(R.id.prev_button)
        nextButton = findViewById(R.id.next_button)
        preguntaTextView = findViewById(R.id.preguntaTextView)
        numPreguntaTextView = findViewById(R.id.idNumPreguntas)
        Opcion1 = findViewById(R.id.btnOpcion1)
        Opcion2 = findViewById(R.id.btnOpcion2)
        Opcion3 = findViewById(R.id.btnOpcion3)
        Opcion4 = findViewById(R.id.btnOpcion4)
        PuntuacionTotal = findViewById(R.id.PuntuacionTextView) // <- score Total
        numPistas = findViewById(R.id.btnPistas)

        // obtiene los settings, si los encuentra los asigna a settings.
        val savedUser = ModelPreferencesManager.get<Users>("USER")
        if(savedUser != null) {
            model.user = savedUser
        }

        // obtener el banco de preguntas desde base de datos
        var questions = repository.getAllQuestions()
        val observer = Observer<List<Question>> { questions ->
            if (questions != null) {
                model.questionBank = questions.toMutableList()

                // obtener un juego activo, si no hay se debe crear uno en ese momento.
                var currentGame = repository.getActiveGameByUser(model.user.userId)
                val observer2 = Observer<GameWithSelectedQuestions> { game ->
                    if (game != null) {
                        if(!alertShowed) {
                            val builder = AlertDialog.Builder(this)
                            builder.setMessage("Se detectó una partida, deseas continuarla?")
                                .setCancelable(false)
                                .setPositiveButton("Yes") { dialog, id ->
                                    model.game = game.game
                                    model.currentIndex = game.game.currentQuestion - 1
                                    if(model.selectedQuestions.isEmpty() && !game.selectedQuestions.isEmpty()) {
                                        model.selectedQuestions = game.selectedQuestions.toMutableList()
                                    }
                                    PuntuacionTotal.text = "${model.totalPuntos()} pts"
                                    numPistas.setText("Pistas: ${model.game.numPistas}")
                                    refreshGameQuestions(game)
                                }
                                .setNegativeButton("No") { dialog, id ->
                                    repository.deleteGame(game.game)
                                    newGameProcess()
                                }
                            val alert = builder.create()
                            alert.show()
                            alertShowed = true
                        } else {
                            model.game = game.game
                            model.currentIndex = game.game.currentQuestion - 1
                            if(model.selectedQuestions.isEmpty() && !game.selectedQuestions.isEmpty()) {
                                model.selectedQuestions = game.selectedQuestions.toMutableList()
                                //PuntuacionTotal.text = "${model.totalPuntos()} pts"
                                //numPistas.setText("Pistas: ${model.game.numPistas}")
                            }
                            refreshGameQuestions(game)
                        }
                    } else {
                        if(!alertShowed) {
                            alertShowed = true
                            newGameProcess()
                        }
                    }
                }
                currentGame.observe(this, observer2)

            } else {
                makeText(
                    this,
                    "Preguntas no encontradas",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        questions.observe(this, observer)


        // ocultar opciones dependiendo de dificultad

        // dificultad media - ocultar ultima pregunta
        if(model.user.dificultad == 2) {
            Opcion4.setVisibility(View.GONE)
        }

        // dificultad baja - ocultar ultimas 2 preguntas
        if(model.user.dificultad == 1) {
            Opcion3.setVisibility(View.GONE)
            Opcion4.setVisibility(View.GONE)
        }

        if(!model.user.habilitarPistas) {
            numPistas.setVisibility(View.INVISIBLE)
        } else {
            numPistas.setText("Pistas: ${model.user.numPistas}")
        }

        prevButton.setOnClickListener { view: View ->
            model.prevQuestion()
            model.game.currentQuestion = model.currentQuestionNumber
            repository.updateGame(model.game)
            loadQuestion()
        }
        nextButton.setOnClickListener { view: View ->
            model.nextQuestion()
            model.game.currentQuestion = model.currentQuestionNumber
            repository.updateGame(model.game)
            loadQuestion()
        }
        numPistas.setOnClickListener { view: View ->
            // si no esta respondida, te quedan pistas y hay al menos 2 botones sin bloquear continúa
            if(!model.currentQuestion.isAnswered() && model.user.numPistas > 0 && model.notLockedButtons() >= 2) {
                model.user.numPistas--
                model.game.numPistas = model.user.numPistas
                repository.updateGame(model.game)
                model.blockButton()
                loadQuestion()
                numPistas.setText("Pistas: ${model.user.numPistas}")
            }
        }

        //Opción1 ---
        Opcion1.setOnClickListener { view: View ->
            optionEvent(1)
        }
        Opcion2.setOnClickListener { view: View ->
            optionEvent(2)
        }
        Opcion3.setOnClickListener { view: View ->
            optionEvent(3)
        }
        Opcion4.setOnClickListener { view: View ->
            optionEvent(4)
        }
    }

    fun newGameProcess() {  //proceso del juego *
        repository.insertGame(Game(0,model.user.userId,true,0, Date()))
        // obtener juego recien agregado
        var currentGame = repository.getActiveGameByUser(model.user.userId)

        val observer3 = Observer<GameWithSelectedQuestions> { game ->
            if(game != null) {
                model.game = game.game
                model.game.numPistas = model.user.numPistas
                if(model.selectedQuestions.isEmpty() && !game.selectedQuestions.isEmpty()) {
                    model.selectedQuestions = game.selectedQuestions.toMutableList()
                }
                refreshGameQuestions(game)
            }
        }
        currentGame.observe(this, observer3)

    }

    fun refreshGameQuestions(game: GameWithSelectedQuestions) {
        if(game.selectedQuestions.isEmpty() && model.selectedQuestions.isEmpty()) {
            model.filterQuestions()
            for(selectedQuestion in model.selectedQuestions) {
                //selectedQuestion.gameId = newGame.
                selectedQuestion.selectedQuestion.gameId = model.game.gameId
                var selectedId = repository.insertSelectedQuestion(selectedQuestion.selectedQuestion)
                selectedQuestion.selectedQuestion.selectedQuestionId = selectedId.toInt();
            }
            game.selectedQuestions = model.selectedQuestions
        }
        loadQuestion()
    }

    fun optionEvent(selectedOption : Int) {
        if (model.currentQuestion.isAnswered()) {
            Toast.makeText(
                this,
                if (model.currentQuestion.isCorrect()) "Ya has contestado correctamente"
                else "Ya has contestado incorrectamente",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            // se selecciono la opcion
            model.currentQuestion.selectedQuestion.answer = selectedOption

            repository.updateSelectedQuestion(model.currentQuestion.selectedQuestion)

            // mensaje si la respuesta fue correcta o no
            val result = if (model.currentQuestion.isCorrect()) "correcto" else "incorrecto"
            Toast.makeText(
                this,
                result,
                Toast.LENGTH_SHORT
            ).show()

            //si es correcta suma puntaje
            /*
            if(model.currentQuestion.isCorrect()) {
                model.puntuacion_actual++
            }
            */

            if(model.gameFinished()) {
                // PuntuacionTotal.text = "Final: ${(model.numberOfGoodAnswers.toFloat() / (model.questionsSize).toFloat()) * 100} pts"
                var maxPuntos = model.selectedQuestions.size * model.user.dificultad
                var totalPuntos = model.totalPuntos()
                var porcentaje : Int = (((totalPuntos.toFloat()/maxPuntos.toFloat()).toFloat())*100).roundToInt()
                PuntuacionTotal.text = "final: ${totalPuntos} pts ${porcentaje}%"

                model.game.isActive = false
                model.game.date = Date()
                model.game.score = totalPuntos
                repository.updateGame(model.game)

                val intent = Intent(this, FinalScoreActivity::class.java)
                intent.putExtra("Porcentaje", porcentaje)
                startActivity(intent)
                // if (model.puntuacion_actual == model.questionsSize) {
                    Toast.makeText(
                        this,
                        "Game Over",
                        Toast.LENGTH_SHORT
                    ).show()
                //}

            } else {
                model.game.score = model.totalPuntos()
                repository.updateGame(model.game)
                PuntuacionTotal.text = "${model.totalPuntos()} pts"
            }

            AnsColor()
        }
    }

    fun loadQuestion() {
        preguntaTextView.setText(model.currentQuestion.question.pregunta)
        numPreguntaTextView.setText("${model.currentQuestionNumber}/${model.questionsSize}")
        Opcion1.setText(model.currentQuestion.selectedQuestion.answer1)
        Opcion2.setText(model.currentQuestion.selectedQuestion.answer2)
        if(model.user.dificultad >= 2) {
            Opcion3.setText(model.currentQuestion.selectedQuestion.answer3)
        }
        if(model.user.dificultad == 3) {
            Opcion4.setText(model.currentQuestion.selectedQuestion.answer4)
        }
        AnsColor()
    }

    //Cambia de color Dependiendo de la respuesta  *
    fun AnsColor() {
        var green : Int = Color.parseColor("#008F39")
        var red : Int = Color.parseColor("#FF0000")
        var white : Int = Color.parseColor("#FFFFFF")
        var gray : Int = Color.parseColor("#616161")

        Opcion1.setTextColor(white)
        Opcion2.setTextColor(white)
        Opcion3.setTextColor(white)
        Opcion4.setTextColor(white)

        if(model.currentQuestion.selectedQuestion.answer1Locked) {
            Opcion1.setTextColor(gray)
            Opcion1.setEnabled(false)
        } else {
            Opcion1.setEnabled(true)
        }

        if(model.currentQuestion.selectedQuestion.answer2Locked) {
            Opcion2.setTextColor(gray)
            Opcion2.setEnabled(false)
        } else {
            Opcion2.setEnabled(true)
        }

        if(model.currentQuestion.selectedQuestion.answer3Locked) {
            Opcion3.setTextColor(gray)
            Opcion3.setEnabled(false)
        } else {
            Opcion3.setEnabled(true)
        }

        if(model.currentQuestion.selectedQuestion.answer4Locked) {
            Opcion4.setTextColor(gray)
            Opcion4.setEnabled(false)
        } else {
            Opcion4.setEnabled(true)
        }

        if (model.currentQuestion.isAnswered()) {
            if(model.currentQuestion.selectedQuestion.answer == 1) {
                if (model.currentQuestion.isCorrect()) {
                    Opcion1.setTextColor(green) // verde
                } else {
                    Opcion1.setTextColor(red) // rojo
                }
            }
            if(model.currentQuestion.selectedQuestion.answer == 2) {
                if (model.currentQuestion.isCorrect()) {
                    Opcion2.setTextColor(green) // verde
                } else {
                    Opcion2.setTextColor(red) // rojo
                }
            }
            if(model.currentQuestion.selectedQuestion.answer == 3) {
                if (model.currentQuestion.isCorrect()) {
                    Opcion3.setTextColor(green) // verde
                } else {
                    Opcion3.setTextColor(red) // rojo
                }
            }
            if(model.currentQuestion.selectedQuestion.answer == 4) {
                if (model.currentQuestion.isCorrect()) {
                    Opcion4.setTextColor(green) // verde
                } else {
                    Opcion4.setTextColor(red) // rojo
                }
            }
        }
    }

    override fun onBackPressed() {

        val builder = AlertDialog.Builder(this)
        builder.setMessage("Desea salir del juego?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                super.onBackPressed()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}