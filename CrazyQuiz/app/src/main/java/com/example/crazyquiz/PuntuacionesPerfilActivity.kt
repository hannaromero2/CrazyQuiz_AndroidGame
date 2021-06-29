package com.example.crazyquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crazyquiz.db.*
import com.example.crazyquiz.modelo.Puntuaciones
import com.example.crazyquiz.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_puntuaciones_perfil.*

class PuntuacionesPerfilActivity : AppCompatActivity(),RecyclerAdapter.OnPuntajeClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var repository: QuizRepository
    private lateinit var user: Users
    private lateinit var listPuntaje: MutableList<Puntuaciones>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntuaciones_perfil)
        setSupportActionBar(findViewById(R.id.toolbar))
        repository = QuizRepository(this.application)
        recyclerView = findViewById(R.id.reciclerview)
        listPuntaje = mutableListOf<Puntuaciones>()

        var savedUser = ModelPreferencesManager.get<Users>("USER")
        if(savedUser != null) {
            user = savedUser
            setupRecyclerView()
        }


    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun showItemToast(msg:String) : Boolean {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        return true
    }

    fun orderByDate() {
        // función de orden descendente para filtro por fecha
        listPuntaje.sortByDescending { it.user.lastDate }
        recyclerView.adapter = RecyclerAdapter(this, listPuntaje,this)
        Toast.makeText(this, "fecha", Toast.LENGTH_SHORT).show()
    }

    fun orderByScore() {
        // función de orden descendente para filtro por puntaje global
        listPuntaje.sortByDescending { it.user.globalScore }
        recyclerView.adapter = RecyclerAdapter(this, listPuntaje,this)
        Toast.makeText(this, "puntaje", Toast.LENGTH_SHORT).show()
    }

    // eventos del toolbae
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.date_menu_item, -> orderByDate()
            R.id.points_menu_item, -> orderByScore()
            else -> super.onOptionsItemSelected(item)

        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        reciclerview.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration((DividerItemDecoration(this,DividerItemDecoration.VERTICAL)))

        var users = repository.getUsers();

//        var currentGame = repository.getActiveGameByUser(model.user.userId)
        val observer = Observer<List<UserWithGames>> { users ->
            for(user in users) {
                listPuntaje.add(Puntuaciones(user.user.userName,"https://www.tierragamer.com/wp-content/uploads/2019/08/One-Piece-Monkey-D-Luffy-Cosplay-Gato.jpg",user))
            }
            recyclerView.adapter = RecyclerAdapter(this, listPuntaje,this)
        }
        users.observe(this, observer)
    }

    override fun onImageClick(imagen: String) {
     val intent = Intent(this,PerfilDetail::class.java)
        intent.putExtra("imageUrl", imagen)
        startActivity(intent)

    }

    // click de cada fila de recyclerview
    override fun onItemClick(user: UserWithGames) {
        var message: String = "";
        var cont: Int = 1
        //obtiene mensaje para desglose de juegos
        for(game in user.games) {
            message += "P$cont: score ${game.game.score}, pistas: ${game.pistasUsadas} \n"
            cont++
        }

        // muestra alertdialog con mensaje
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
            }
        val alert = builder.create()
        alert.show()
        //Toast.makeText(this,"Info: ${user.globalScore} ${user.lastDate}", Toast.LENGTH_SHORT).show()
    }
}

