package com.example.crazyquiz

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.crazyquiz.base.BaseViewHolder
import com.example.crazyquiz.db.UserWithGames
import com.example.crazyquiz.modelo.Puntuaciones
import kotlinx.android.synthetic.main.puntaje_row.view.*
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class RecyclerAdapter (private val context: Context, val listaPuntaje:List<Puntuaciones>, val itemClickListener:OnPuntajeClickListener): RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnPuntajeClickListener{
      fun onImageClick(imagen: String)
      fun onItemClick(usr: UserWithGames)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return PuntajeViewHolder(LayoutInflater.from(context).inflate(R.layout.puntaje_row,parent,false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
       when(holder){
           is PuntajeViewHolder -> holder.bind(listaPuntaje[position],position)
           else -> throw IllegalArgumentException("Se olvido de pasar l viewholder en el bind")
       }
    }

    override fun getItemCount(): Int = listaPuntaje.size

    inner class PuntajeViewHolder(itemView: View):BaseViewHolder<Puntuaciones>(itemView){
        override fun bind(item: Puntuaciones, position: Int) {

            // se definen los eventos de click para cada fila
            itemView.setOnClickListener{itemClickListener.onItemClick(item.user)}
            itemView.img_perfil.setOnClickListener{itemClickListener.onImageClick(item.imagen)}
            itemView.txt_puntaje.setText(item.user.globalScore.toString())

            // poner fecha del último juego
            var lastDate: Date? = item.user.lastDate
            if(lastDate != null) {
                val pattern = "yyyy-MM-dd HH:mm:ss"
                val simpleDateFormat = SimpleDateFormat(pattern)
                itemView.txt_fecha.setText(simpleDateFormat.format(lastDate))
            } else {
                itemView.txt_fecha.setText("-----")
            }

            //itemView.txt_pistas.setText(item.game.game.numPistas.toString())
            Glide.with(context).load(item.imagen).into(itemView.img_perfil)
            itemView.txt_usuario1.text = item.nombre
        }
    }
}