package com.example.crazyquiz

import androidx.lifecycle.ViewModel
import com.example.crazyquiz.db.*
import kotlin.random.Random

class GameModel : ViewModel() {

    //CATEGORIAS

    val HARRY_POTTER = 1
    val CATS_REPTILES = 2
    val FOOD = 3
    val TERROR = 4
    val CULTURA_GENERAL = 5
    val ARTE_GEOGRAFIA = 6

    lateinit var user: Users
    lateinit var game: Game
    lateinit var questionBank: MutableList<Question>
    lateinit var selectedQuestions: MutableList<SelectedQuestionAndQuestion>

    init {
        user = Users(0,"","","",true, false, false, false, false, false, false, "6", 2, false, 2)
        selectedQuestions = mutableListOf<SelectedQuestionAndQuestion>()
        //setQuestionBank()
    }

    /*
    fun setQuestionBank() {
        questionBank = mutableListOf<Question>()
        questionBank.addAll(
            listOf(
                Question(R.string.question_text_1, R.string.p1_respuesta_1, R.string.p1_respuesta_1, R.string.p1_respuesta_2, R.string.p1_respuesta_3, R.string.p1_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_2, R.string.p2_respuesta_1, R.string.p2_respuesta_1, R.string.p2_respuesta_2, R.string.p2_respuesta_3, R.string.p2_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_3, R.string.p3_respuesta_1, R.string.p3_respuesta_1, R.string.p3_respuesta_2, R.string.p3_respuesta_3, R.string.p3_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_4, R.string.p4_respuesta_1, R.string.p4_respuesta_1, R.string.p4_respuesta_2, R.string.p4_respuesta_3, R.string.p4_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_5, R.string.p5_respuesta_1, R.string.p5_respuesta_1, R.string.p5_respuesta_2, R.string.p5_respuesta_3, R.string.p5_respuesta_4, HARRY_POTTER) ,
                Question(R.string.question_text_6, R.string.p6_respuesta_1, R.string.p6_respuesta_1, R.string.p6_respuesta_2, R.string.p6_respuesta_3, R.string.p6_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_7, R.string.p7_respuesta_1, R.string.p7_respuesta_1, R.string.p7_respuesta_2, R.string.p7_respuesta_3, R.string.p7_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_8, R.string.p8_respuesta_1, R.string.p8_respuesta_1, R.string.p8_respuesta_2, R.string.p8_respuesta_3, R.string.p8_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_9, R.string.p9_respuesta_1, R.string.p9_respuesta_1, R.string.p9_respuesta_2, R.string.p9_respuesta_3, R.string.p9_respuesta_4, HARRY_POTTER),
                Question(R.string.question_text_10, R.string.p10_respuesta_1, R.string.p10_respuesta_1, R.string.p10_respuesta_2, R.string.p10_respuesta_3, R.string.p10_respuesta_4, HARRY_POTTER),

                Question(R.string.question_text_11, R.string.p11_respuesta_1, R.string.p11_respuesta_1, R.string.p11_respuesta_2, R.string.p11_respuesta_3, R.string.p11_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_12, R.string.p12_respuesta_1, R.string.p12_respuesta_1, R.string.p12_respuesta_2, R.string.p12_respuesta_3, R.string.p12_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_13, R.string.p13_respuesta_1, R.string.p13_respuesta_1, R.string.p13_respuesta_2, R.string.p13_respuesta_3, R.string.p13_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_14, R.string.p14_respuesta_1, R.string.p14_respuesta_1, R.string.p14_respuesta_2, R.string.p14_respuesta_3, R.string.p14_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_15, R.string.p15_respuesta_1, R.string.p15_respuesta_1, R.string.p15_respuesta_2, R.string.p15_respuesta_3, R.string.p15_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_16, R.string.p16_respuesta_1, R.string.p16_respuesta_1, R.string.p16_respuesta_2, R.string.p16_respuesta_3, R.string.p16_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_17, R.string.p17_respuesta_1, R.string.p17_respuesta_1, R.string.p17_respuesta_2, R.string.p17_respuesta_3, R.string.p17_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_18, R.string.p18_respuesta_1, R.string.p18_respuesta_1, R.string.p18_respuesta_2, R.string.p18_respuesta_3, R.string.p18_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_19, R.string.p19_respuesta_1, R.string.p19_respuesta_1, R.string.p19_respuesta_2, R.string.p19_respuesta_3, R.string.p19_respuesta_4, CATS_REPTILES),
                Question(R.string.question_text_20, R.string.p20_respuesta_1, R.string.p20_respuesta_1, R.string.p20_respuesta_2, R.string.p20_respuesta_3, R.string.p20_respuesta_4, CATS_REPTILES),

                Question(R.string.question_text_21, R.string.p21_respuesta_1, R.string.p21_respuesta_1, R.string.p21_respuesta_2, R.string.p21_respuesta_3, R.string.p21_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_22, R.string.p22_respuesta_1, R.string.p22_respuesta_1, R.string.p22_respuesta_2, R.string.p22_respuesta_3, R.string.p22_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_23, R.string.p23_respuesta_1, R.string.p23_respuesta_1, R.string.p23_respuesta_2, R.string.p23_respuesta_3, R.string.p23_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_24, R.string.p24_respuesta_1, R.string.p24_respuesta_1, R.string.p24_respuesta_2, R.string.p24_respuesta_3, R.string.p24_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_25, R.string.p25_respuesta_1, R.string.p25_respuesta_1, R.string.p25_respuesta_2, R.string.p25_respuesta_3, R.string.p25_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_26, R.string.p26_respuesta_1, R.string.p26_respuesta_1, R.string.p26_respuesta_2, R.string.p26_respuesta_3, R.string.p26_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_27, R.string.p27_respuesta_1, R.string.p27_respuesta_1, R.string.p27_respuesta_2, R.string.p27_respuesta_3, R.string.p27_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_28, R.string.p28_respuesta_1, R.string.p28_respuesta_1, R.string.p28_respuesta_2, R.string.p28_respuesta_3, R.string.p28_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_29, R.string.p29_respuesta_1, R.string.p29_respuesta_1, R.string.p29_respuesta_2, R.string.p29_respuesta_3, R.string.p29_respuesta_4, CULTURA_GENERAL),
                Question(R.string.question_text_30, R.string.p30_respuesta_1, R.string.p30_respuesta_1, R.string.p30_respuesta_2, R.string.p30_respuesta_3, R.string.p30_respuesta_4, CULTURA_GENERAL),

                Question(R.string.question_text_31, R.string.p31_respuesta_1, R.string.p31_respuesta_1, R.string.p31_respuesta_2, R.string.p31_respuesta_3, R.string.p31_respuesta_4, FOOD),
                Question(R.string.question_text_32, R.string.p32_respuesta_1, R.string.p32_respuesta_1, R.string.p32_respuesta_2, R.string.p32_respuesta_3, R.string.p32_respuesta_4, FOOD),
                Question(R.string.question_text_33, R.string.p33_respuesta_1, R.string.p33_respuesta_1, R.string.p33_respuesta_2, R.string.p33_respuesta_3, R.string.p33_respuesta_4, FOOD),
                Question(R.string.question_text_34, R.string.p34_respuesta_1, R.string.p34_respuesta_1, R.string.p34_respuesta_2, R.string.p34_respuesta_3, R.string.p34_respuesta_4, FOOD),
                Question(R.string.question_text_35, R.string.p35_respuesta_1, R.string.p35_respuesta_1, R.string.p35_respuesta_2, R.string.p35_respuesta_3, R.string.p35_respuesta_4, FOOD),
                Question(R.string.question_text_36, R.string.p36_respuesta_1, R.string.p36_respuesta_1, R.string.p36_respuesta_2, R.string.p36_respuesta_3, R.string.p36_respuesta_4, FOOD),
                Question(R.string.question_text_37, R.string.p37_respuesta_1, R.string.p37_respuesta_1, R.string.p37_respuesta_2, R.string.p37_respuesta_3, R.string.p37_respuesta_4, FOOD),
                Question(R.string.question_text_38, R.string.p38_respuesta_1, R.string.p38_respuesta_1, R.string.p38_respuesta_2, R.string.p38_respuesta_3, R.string.p38_respuesta_4, FOOD),
                Question(R.string.question_text_39, R.string.p39_respuesta_1, R.string.p39_respuesta_1, R.string.p39_respuesta_2, R.string.p39_respuesta_3, R.string.p39_respuesta_4, FOOD),
                Question(R.string.question_text_40, R.string.p40_respuesta_1, R.string.p40_respuesta_1, R.string.p40_respuesta_2, R.string.p40_respuesta_3, R.string.p40_respuesta_4, FOOD),

                Question(R.string.question_text_41, R.string.p41_respuesta_1, R.string.p41_respuesta_1, R.string.p41_respuesta_2, R.string.p41_respuesta_3, R.string.p41_respuesta_4, TERROR),
                Question(R.string.question_text_42, R.string.p42_respuesta_1, R.string.p42_respuesta_1, R.string.p42_respuesta_2, R.string.p42_respuesta_3, R.string.p42_respuesta_4, TERROR),
                Question(R.string.question_text_43, R.string.p43_respuesta_1, R.string.p43_respuesta_1, R.string.p43_respuesta_2, R.string.p43_respuesta_3, R.string.p43_respuesta_4, TERROR),
                Question(R.string.question_text_44, R.string.p44_respuesta_1, R.string.p44_respuesta_1, R.string.p44_respuesta_2, R.string.p44_respuesta_3, R.string.p44_respuesta_4, TERROR),
                Question(R.string.question_text_45, R.string.p45_respuesta_1, R.string.p45_respuesta_1, R.string.p45_respuesta_2, R.string.p45_respuesta_3, R.string.p45_respuesta_4, TERROR),
                Question(R.string.question_text_46, R.string.p46_respuesta_1, R.string.p46_respuesta_1, R.string.p46_respuesta_2, R.string.p46_respuesta_3, R.string.p46_respuesta_4, TERROR),
                Question(R.string.question_text_47, R.string.p47_respuesta_1, R.string.p47_respuesta_1, R.string.p47_respuesta_2, R.string.p47_respuesta_3, R.string.p47_respuesta_4, TERROR),
                Question(R.string.question_text_48, R.string.p48_respuesta_1, R.string.p48_respuesta_1, R.string.p48_respuesta_2, R.string.p48_respuesta_3, R.string.p48_respuesta_4, TERROR),
                Question(R.string.question_text_49, R.string.p49_respuesta_1, R.string.p49_respuesta_1, R.string.p49_respuesta_2, R.string.p49_respuesta_3, R.string.p49_respuesta_4, TERROR),
                Question(R.string.question_text_50, R.string.p50_respuesta_1, R.string.p50_respuesta_1, R.string.p50_respuesta_2, R.string.p50_respuesta_3, R.string.p50_respuesta_4, TERROR),

                Question(R.string.question_text_51, R.string.p51_respuesta_1, R.string.p51_respuesta_1, R.string.p51_respuesta_2, R.string.p51_respuesta_3, R.string.p51_respuesta_4, ARTE_GEOGRAFIA ),
                Question(R.string.question_text_52, R.string.p52_respuesta_1, R.string.p52_respuesta_1, R.string.p52_respuesta_2, R.string.p52_respuesta_3, R.string.p52_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_53, R.string.p53_respuesta_1, R.string.p53_respuesta_1, R.string.p53_respuesta_2, R.string.p53_respuesta_3, R.string.p53_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_54, R.string.p54_respuesta_1, R.string.p54_respuesta_1, R.string.p54_respuesta_2, R.string.p54_respuesta_3, R.string.p54_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_55, R.string.p55_respuesta_1, R.string.p55_respuesta_1, R.string.p55_respuesta_2, R.string.p55_respuesta_3, R.string.p55_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_56, R.string.p56_respuesta_1, R.string.p56_respuesta_1, R.string.p56_respuesta_2, R.string.p56_respuesta_3, R.string.p56_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_57, R.string.p57_respuesta_1, R.string.p57_respuesta_1, R.string.p57_respuesta_2, R.string.p57_respuesta_3, R.string.p57_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_58, R.string.p58_respuesta_1, R.string.p58_respuesta_1, R.string.p58_respuesta_2, R.string.p58_respuesta_3, R.string.p58_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_59, R.string.p59_respuesta_1, R.string.p59_respuesta_1, R.string.p59_respuesta_2, R.string.p59_respuesta_3, R.string.p59_respuesta_4, ARTE_GEOGRAFIA),
                Question(R.string.question_text_60, R.string.p60_respuesta_1, R.string.p60_respuesta_1, R.string.p60_respuesta_2, R.string.p60_respuesta_3, R.string.p60_respuesta_4, ARTE_GEOGRAFIA),
            )
        )
    }
    */
    //----
    var numberOfGoodAnswers: Int = 0
    //var puntuacion_actual: Int =  0

    public var currentIndex = 0

    val currentQuestion: SelectedQuestionAndQuestion
        get() = selectedQuestions[currentIndex]

    val questionsSize: Int
        get() = selectedQuestions.size

    val currentQuestionNumber: Int
        get() = currentIndex + 1

    fun nextQuestion() {
        currentIndex = (currentIndex + 1) % selectedQuestions.size
    }

    fun prevQuestion() {
        if (currentIndex > 0) {
//            currentIndex = selectedQuestions.size
//        } else {
            currentIndex =  (currentIndex - 1) % selectedQuestions.size
        }
    }

    fun totalPuntos(): Int {
        var puntos: Int = 0

        // 3 puntos para alta, 2 para media y 1 para baja
        var maxPerQuestion: Int = user.dificultad
        selectedQuestions.forEach() {
            if(it.isCorrect()) {
                // se agregan los puntos maximos, pero se restan puntos si se usa alguna pista
                puntos += maxPerQuestion
                if(it.selectedQuestion.answer1Locked) {
                    puntos--
                }
                if(it.selectedQuestion.answer2Locked) {
                    puntos--
                }
                if(it.selectedQuestion.answer3Locked) {
                    puntos--
                }
                if(it.selectedQuestion.answer4Locked) {
                    puntos--
                }
            }
        }
        return puntos
    }

    fun gameFinished(): Boolean {
        selectedQuestions.forEach() {
            if(!it.isAnswered()) {
                return false;
            }
        }
        return true;
    }

    fun notLockedButtons() : Int {
        var notLockedButtons : Int = 0
        if(!currentQuestion.selectedQuestion.answer1Locked) {
            notLockedButtons++
        }
        if(!currentQuestion.selectedQuestion.answer2Locked) {
            notLockedButtons++
        }
        if(currentQuestion.selectedQuestion.answer3.equals("") && !currentQuestion.selectedQuestion.answer3Locked) {
            notLockedButtons++
        }
        if(currentQuestion.selectedQuestion.answer4.equals("") && !currentQuestion.selectedQuestion.answer4Locked) {
            notLockedButtons++
        }
        return notLockedButtons
    }

    fun blockButton() {
        var buttonFound : Boolean = false

        if(notLockedButtons() < 2) {
            buttonFound = true
        }

        while (!buttonFound) {
            var randomNumber = rand(1,4)
            // si reviso la primera respuesta, y no esta bloqueada y NO ES LA RESPUESTA CORRECTA
            if(randomNumber == 1 && !currentQuestion.selectedQuestion.answer1Locked && !currentQuestion.selectedQuestion.answer1.equals(currentQuestion.question.answer1)) {
                currentQuestion.selectedQuestion.answer1Locked = true
                buttonFound = true
            }
            if(randomNumber == 2 && !currentQuestion.selectedQuestion.answer2Locked && !currentQuestion.selectedQuestion.answer2.equals(currentQuestion.question.answer1)) {
                currentQuestion.selectedQuestion.answer2Locked = true
                buttonFound = true
            }
            if(randomNumber == 3 && !currentQuestion.selectedQuestion.answer3Locked && !currentQuestion.selectedQuestion.answer3.equals(currentQuestion.question.answer1)) {
                currentQuestion.selectedQuestion.answer3Locked = true
                buttonFound = true
            }
            if(randomNumber == 4 && !currentQuestion.selectedQuestion.answer4Locked && !currentQuestion.selectedQuestion.answer4.equals(currentQuestion.question.answer1)) {
                currentQuestion.selectedQuestion.answer4Locked = true
                buttonFound = true
            }
        }
    }

    fun filterQuestions() {
        // filtrado de preguntas por categoría
        var filteredQuestions: MutableList<Question> = ArrayList<Question>();

        //ciclo para filtrar
        questionBank.forEach() {

            // si esta marcado "todos" se agrega a fuerza
            if(user.allThemes) {
                filteredQuestions.add(it)
            } else {

                //si las categorias estan marcadas en settings esas se agregan a la lista nueva
                if(
                    (user.harryPotter && it.categoria == HARRY_POTTER) ||
                    (user.catReptiles && it.categoria == CATS_REPTILES) ||
                    (user.culturaGen && it.categoria == CULTURA_GENERAL) ||
                    (user.food && it.categoria == FOOD) ||
                    (user.terror && it.categoria == TERROR) ||
                    (user.arteGeo && it.categoria == ARTE_GEOGRAFIA)
                ) {
                    filteredQuestions.add(it)
                }
            }
        }

        var x : Int = 0

        // ciclo para acumular preguntas aleatoreamente
        while(x < user.numPreguntas.toInt()) {

            // se obtiene un numero al azar no mayor al numero de elementos de la lista
            var index = rand(0, filteredQuestions.size - 1)
            var randomQuestion : Question = filteredQuestions.get(index)

            // si la preguntas aleatoria no se ha agregado, se agregará.
            if(selectedQuestions.find { it.question.preguntaId == randomQuestion.preguntaId } == null) {

                // dificultad alta
                if(user.dificultad == 3) {
                    // lista de las 4 posibles respuestas de la pregunta seleccionada
                    var randomAnswers = mutableListOf<String>(randomQuestion.answer1, randomQuestion.answer2, randomQuestion.answer3, randomQuestion.answer4)

                    // las respuesta se ordenan al azar
                    randomAnswers.shuffle()

                    // la pregunta y el nuevo order de respuestas se agrega a la lista de "selectedQuestions"
                    var currentSelectedQuestion: SelectedQuestion = SelectedQuestion(
                        0,
                        0,
                        randomQuestion.preguntaId,
                        game.gameId,
                        randomAnswers[0],
                        randomAnswers[1],
                        randomAnswers[2],
                        randomAnswers[3]
                    )
                    selectedQuestions.add(SelectedQuestionAndQuestion(currentSelectedQuestion,randomQuestion))
                }

                // dificultad media
                if(user.dificultad == 2) {
                    // lista de las 4 posibles respuestas de la pregunta seleccionada
                    var randomAnswers = mutableListOf<String>(randomQuestion.answer1, randomQuestion.answer2, randomQuestion.answer3)

                    // las respuesta se ordenan al azar
                    randomAnswers.shuffle()

                    // la pregunta y el nuevo order de respuestas se agrega a la lista de "selectedQuestions"
                    var currentSelectedQuestion: SelectedQuestion = SelectedQuestion(
                        0,
                        0,
                        randomQuestion.preguntaId,
                        game.gameId,
                        randomAnswers[0],
                        randomAnswers[1],
                        randomAnswers[2]
                    )
                    selectedQuestions.add(SelectedQuestionAndQuestion(currentSelectedQuestion,randomQuestion))
                }

                // dificultad baja
                if(user.dificultad == 1) {
                    // lista de las 4 posibles respuestas de la pregunta seleccionada
                    var randomAnswers = mutableListOf<String>(randomQuestion.answer1, randomQuestion.answer2)

                    // las respuesta se ordenan al azar
                    randomAnswers.shuffle()

                    // la pregunta y el nuevo order de respuestas se agrega a la lista de "selectedQuestions"
                    var currentSelectedQuestion: SelectedQuestion = SelectedQuestion(
                        0,
                        0,
                        randomQuestion.preguntaId,
                        game.gameId,
                        randomAnswers[0],
                        randomAnswers[1]
                    )
                    selectedQuestions.add(SelectedQuestionAndQuestion(currentSelectedQuestion,randomQuestion))
                }
                x++
            }
        }
    }

    fun rand(start: Int, end: Int): Int {
        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
        return Random(System.nanoTime()).nextInt(end - start + 1) + start
    }

    override fun onCleared() {
        super.onCleared()
        //Log.d()
    }
}
