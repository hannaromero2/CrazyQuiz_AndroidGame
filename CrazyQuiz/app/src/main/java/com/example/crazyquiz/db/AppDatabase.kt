
package com.example.crazyquiz.db

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.crazyquiz.GameModel
import com.example.crazyquiz.R
import com.example.crazyquiz.db.Users
import com.example.crazyquiz.db.UsersDao


@Database(
    entities = [Users:: class, Question::class, SelectedQuestion::class, Game::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun UsersDao(): UsersDao
    abstract fun QuestionDao(): QuestionDao
    abstract fun SelectedQuestionDao(): SelectedQuestionDao
    abstract fun GameDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "quiz_database"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                .allowMainThreadQueries()
                .addCallback(object : RoomDatabase.Callback() {

                    fun getStr(strId : Int) : String {
                        return context.getString(strId)
                    }

                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val HARRY_POTTER = 1
                        val CATS_REPTILES = 2
                        val FOOD = 3
                        val TERROR = 4
                        val CULTURA_GENERAL = 5
                        val ARTE_GEOGRAFIA = 6

                        /* HARRY_POTTER*/
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_1)}', 1, '${getStr(R.string.p1_respuesta_1)}', '${getStr(R.string.p1_respuesta_2)}', '${getStr(R.string.p1_respuesta_3)}', '${getStr(R.string.p1_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_2)}', 1, '${getStr(R.string.p2_respuesta_1)}', '${getStr(R.string.p2_respuesta_2)}', '${getStr(R.string.p2_respuesta_3)}', '${getStr(R.string.p2_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_3)}', 1, '${getStr(R.string.p3_respuesta_1)}', '${getStr(R.string.p3_respuesta_2)}', '${getStr(R.string.p3_respuesta_3)}', '${getStr(R.string.p3_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_4)}', 1, '${getStr(R.string.p4_respuesta_1)}', '${getStr(R.string.p4_respuesta_2)}', '${getStr(R.string.p4_respuesta_3)}', '${getStr(R.string.p4_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_5)}', 1, '${getStr(R.string.p5_respuesta_1)}', '${getStr(R.string.p5_respuesta_2)}', '${getStr(R.string.p5_respuesta_3)}', '${getStr(R.string.p5_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_6)}', 1, '${getStr(R.string.p6_respuesta_1)}', '${getStr(R.string.p6_respuesta_2)}', '${getStr(R.string.p6_respuesta_3)}', '${getStr(R.string.p6_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_7)}', 1, '${getStr(R.string.p7_respuesta_1)}', '${getStr(R.string.p7_respuesta_2)}', '${getStr(R.string.p7_respuesta_3)}', '${getStr(R.string.p7_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_8)}', 1, '${getStr(R.string.p8_respuesta_1)}', '${getStr(R.string.p8_respuesta_2)}', '${getStr(R.string.p8_respuesta_3)}', '${getStr(R.string.p8_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_9)}', 1, '${getStr(R.string.p9_respuesta_1)}', '${getStr(R.string.p9_respuesta_2)}', '${getStr(R.string.p9_respuesta_3)}', '${getStr(R.string.p9_respuesta_4)}',$HARRY_POTTER)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_10)}', 1, '${getStr(R.string.p10_respuesta_1)}', '${getStr(R.string.p10_respuesta_2)}', '${getStr(R.string.p10_respuesta_3)}', '${getStr(R.string.p10_respuesta_4)}',$HARRY_POTTER)")
                       
                       /* CATS_REPTILES*/

                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_11)}', 1, '${getStr(R.string.p11_respuesta_1)}', '${getStr(R.string.p11_respuesta_2)}', '${getStr(R.string.p11_respuesta_3)}', '${getStr(R.string.p11_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_12)}', 1, '${getStr(R.string.p12_respuesta_1)}', '${getStr(R.string.p12_respuesta_2)}', '${getStr(R.string.p12_respuesta_3)}', '${getStr(R.string.p12_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_13)}', 1, '${getStr(R.string.p13_respuesta_1)}', '${getStr(R.string.p13_respuesta_2)}', '${getStr(R.string.p13_respuesta_3)}', '${getStr(R.string.p13_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_14)}', 1, '${getStr(R.string.p14_respuesta_1)}', '${getStr(R.string.p14_respuesta_2)}', '${getStr(R.string.p14_respuesta_3)}', '${getStr(R.string.p14_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_15)}', 1, '${getStr(R.string.p15_respuesta_1)}', '${getStr(R.string.p15_respuesta_2)}', '${getStr(R.string.p15_respuesta_3)}', '${getStr(R.string.p15_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_16)}', 1, '${getStr(R.string.p16_respuesta_1)}', '${getStr(R.string.p16_respuesta_2)}', '${getStr(R.string.p16_respuesta_3)}', '${getStr(R.string.p16_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_17)}', 1, '${getStr(R.string.p17_respuesta_1)}', '${getStr(R.string.p17_respuesta_2)}', '${getStr(R.string.p17_respuesta_3)}', '${getStr(R.string.p17_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_18)}', 1, '${getStr(R.string.p18_respuesta_1)}', '${getStr(R.string.p18_respuesta_2)}', '${getStr(R.string.p18_respuesta_3)}', '${getStr(R.string.p18_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_19)}', 1, '${getStr(R.string.p19_respuesta_1)}', '${getStr(R.string.p19_respuesta_2)}', '${getStr(R.string.p19_respuesta_3)}', '${getStr(R.string.p19_respuesta_4)}',$CATS_REPTILES)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_20)}', 1, '${getStr(R.string.p20_respuesta_1)}', '${getStr(R.string.p20_respuesta_2)}', '${getStr(R.string.p20_respuesta_3)}', '${getStr(R.string.p20_respuesta_4)}',$CATS_REPTILES)")

                      /* FOOD*/

                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_21)}', 1, '${getStr(R.string.p21_respuesta_1)}', '${getStr(R.string.p21_respuesta_2)}', '${getStr(R.string.p21_respuesta_3)}', '${getStr(R.string.p21_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_22)}', 1, '${getStr(R.string.p22_respuesta_1)}', '${getStr(R.string.p22_respuesta_2)}', '${getStr(R.string.p22_respuesta_3)}', '${getStr(R.string.p22_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_23)}', 1, '${getStr(R.string.p23_respuesta_1)}', '${getStr(R.string.p23_respuesta_2)}', '${getStr(R.string.p23_respuesta_3)}', '${getStr(R.string.p23_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_24)}', 1, '${getStr(R.string.p24_respuesta_1)}', '${getStr(R.string.p24_respuesta_2)}', '${getStr(R.string.p24_respuesta_3)}', '${getStr(R.string.p24_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_25)}', 1, '${getStr(R.string.p25_respuesta_1)}', '${getStr(R.string.p25_respuesta_2)}', '${getStr(R.string.p25_respuesta_3)}', '${getStr(R.string.p25_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_26)}', 1, '${getStr(R.string.p26_respuesta_1)}', '${getStr(R.string.p26_respuesta_2)}', '${getStr(R.string.p26_respuesta_3)}', '${getStr(R.string.p26_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_27)}', 1, '${getStr(R.string.p27_respuesta_1)}', '${getStr(R.string.p27_respuesta_2)}', '${getStr(R.string.p27_respuesta_3)}', '${getStr(R.string.p27_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_28)}', 1, '${getStr(R.string.p28_respuesta_1)}', '${getStr(R.string.p28_respuesta_2)}', '${getStr(R.string.p28_respuesta_3)}', '${getStr(R.string.p28_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_29)}', 1, '${getStr(R.string.p29_respuesta_1)}', '${getStr(R.string.p29_respuesta_2)}', '${getStr(R.string.p29_respuesta_3)}', '${getStr(R.string.p29_respuesta_4)}',$FOOD)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_30)}', 1, '${getStr(R.string.p30_respuesta_1)}', '${getStr(R.string.p30_respuesta_2)}', '${getStr(R.string.p30_respuesta_3)}', '${getStr(R.string.p30_respuesta_4)}',$FOOD)")

                        /* TERROR */

                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_31)}', 1, '${getStr(R.string.p31_respuesta_1)}', '${getStr(R.string.p31_respuesta_2)}', '${getStr(R.string.p31_respuesta_3)}', '${getStr(R.string.p31_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_32)}', 1, '${getStr(R.string.p32_respuesta_1)}', '${getStr(R.string.p32_respuesta_2)}', '${getStr(R.string.p32_respuesta_3)}', '${getStr(R.string.p32_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_33)}', 1, '${getStr(R.string.p33_respuesta_1)}', '${getStr(R.string.p33_respuesta_2)}', '${getStr(R.string.p33_respuesta_3)}', '${getStr(R.string.p33_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_34)}', 1, '${getStr(R.string.p34_respuesta_1)}', '${getStr(R.string.p34_respuesta_2)}', '${getStr(R.string.p34_respuesta_3)}', '${getStr(R.string.p34_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_35)}', 1, '${getStr(R.string.p35_respuesta_1)}', '${getStr(R.string.p35_respuesta_2)}', '${getStr(R.string.p35_respuesta_3)}', '${getStr(R.string.p35_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_36)}', 1, '${getStr(R.string.p36_respuesta_1)}', '${getStr(R.string.p36_respuesta_2)}', '${getStr(R.string.p36_respuesta_3)}', '${getStr(R.string.p36_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_37)}', 1, '${getStr(R.string.p37_respuesta_1)}', '${getStr(R.string.p37_respuesta_2)}', '${getStr(R.string.p37_respuesta_3)}', '${getStr(R.string.p37_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_38)}', 1, '${getStr(R.string.p38_respuesta_1)}', '${getStr(R.string.p38_respuesta_2)}', '${getStr(R.string.p38_respuesta_3)}', '${getStr(R.string.p38_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_39)}', 1, '${getStr(R.string.p39_respuesta_1)}', '${getStr(R.string.p39_respuesta_2)}', '${getStr(R.string.p39_respuesta_3)}', '${getStr(R.string.p39_respuesta_4)}',$TERROR)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_40)}', 1, '${getStr(R.string.p40_respuesta_1)}', '${getStr(R.string.p40_respuesta_2)}', '${getStr(R.string.p40_respuesta_3)}', '${getStr(R.string.p40_respuesta_4)}',$TERROR)")

                        /* CULTURA_GENERAL */

                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_41)}', 1, '${getStr(R.string.p41_respuesta_1)}', '${getStr(R.string.p41_respuesta_2)}', '${getStr(R.string.p41_respuesta_3)}', '${getStr(R.string.p41_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_42)}', 1, '${getStr(R.string.p42_respuesta_1)}', '${getStr(R.string.p42_respuesta_2)}', '${getStr(R.string.p42_respuesta_3)}', '${getStr(R.string.p42_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_43)}', 1, '${getStr(R.string.p43_respuesta_1)}', '${getStr(R.string.p43_respuesta_2)}', '${getStr(R.string.p43_respuesta_3)}', '${getStr(R.string.p43_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_44)}', 1, '${getStr(R.string.p44_respuesta_1)}', '${getStr(R.string.p44_respuesta_2)}', '${getStr(R.string.p44_respuesta_3)}', '${getStr(R.string.p44_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_45)}', 1, '${getStr(R.string.p45_respuesta_1)}', '${getStr(R.string.p45_respuesta_2)}', '${getStr(R.string.p45_respuesta_3)}', '${getStr(R.string.p45_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_46)}', 1, '${getStr(R.string.p46_respuesta_1)}', '${getStr(R.string.p46_respuesta_2)}', '${getStr(R.string.p46_respuesta_3)}', '${getStr(R.string.p46_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_47)}', 1, '${getStr(R.string.p47_respuesta_1)}', '${getStr(R.string.p47_respuesta_2)}', '${getStr(R.string.p47_respuesta_3)}', '${getStr(R.string.p47_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_48)}', 1, '${getStr(R.string.p48_respuesta_1)}', '${getStr(R.string.p48_respuesta_2)}', '${getStr(R.string.p48_respuesta_3)}', '${getStr(R.string.p48_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_49)}', 1, '${getStr(R.string.p49_respuesta_1)}', '${getStr(R.string.p49_respuesta_2)}', '${getStr(R.string.p49_respuesta_3)}', '${getStr(R.string.p49_respuesta_4)}',$CULTURA_GENERAL)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_50)}', 1, '${getStr(R.string.p50_respuesta_1)}', '${getStr(R.string.p50_respuesta_2)}', '${getStr(R.string.p50_respuesta_3)}', '${getStr(R.string.p50_respuesta_4)}',$CULTURA_GENERAL)")

                        /* ARTE_GEOGRAFIA */

                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_51)}', 1, '${getStr(R.string.p51_respuesta_1)}', '${getStr(R.string.p51_respuesta_2)}', '${getStr(R.string.p51_respuesta_3)}', '${getStr(R.string.p51_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_52)}', 1, '${getStr(R.string.p52_respuesta_1)}', '${getStr(R.string.p52_respuesta_2)}', '${getStr(R.string.p52_respuesta_3)}', '${getStr(R.string.p52_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_53)}', 1, '${getStr(R.string.p53_respuesta_1)}', '${getStr(R.string.p53_respuesta_2)}', '${getStr(R.string.p53_respuesta_3)}', '${getStr(R.string.p53_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_54)}', 1, '${getStr(R.string.p54_respuesta_1)}', '${getStr(R.string.p54_respuesta_2)}', '${getStr(R.string.p54_respuesta_3)}', '${getStr(R.string.p54_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_55)}', 1, '${getStr(R.string.p55_respuesta_1)}', '${getStr(R.string.p55_respuesta_2)}', '${getStr(R.string.p55_respuesta_3)}', '${getStr(R.string.p55_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_56)}', 1, '${getStr(R.string.p56_respuesta_1)}', '${getStr(R.string.p56_respuesta_2)}', '${getStr(R.string.p56_respuesta_3)}', '${getStr(R.string.p56_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_57)}', 1, '${getStr(R.string.p57_respuesta_1)}', '${getStr(R.string.p57_respuesta_2)}', '${getStr(R.string.p57_respuesta_3)}', '${getStr(R.string.p57_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_58)}', 1, '${getStr(R.string.p58_respuesta_1)}', '${getStr(R.string.p58_respuesta_2)}', '${getStr(R.string.p58_respuesta_3)}', '${getStr(R.string.p58_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_59)}', 1, '${getStr(R.string.p59_respuesta_1)}', '${getStr(R.string.p59_respuesta_2)}', '${getStr(R.string.p59_respuesta_3)}', '${getStr(R.string.p59_respuesta_4)}',$ARTE_GEOGRAFIA)")
                        db.execSQL("INSERT INTO questions(pregunta, correcta, answer1, answer2, answer3, answer4, categoria) VALUES ('${getStr(R.string.question_text_60)}', 1, '${getStr(R.string.p60_respuesta_1)}', '${getStr(R.string.p60_respuesta_2)}', '${getStr(R.string.p60_respuesta_3)}', '${getStr(R.string.p60_respuesta_4)}',$ARTE_GEOGRAFIA)")


                    }
                }).build()
            }
            return INSTANCE
        }
    }
}