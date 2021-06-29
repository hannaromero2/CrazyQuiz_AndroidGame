package com.example.crazyquiz

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_perfil_detail.*

class PerfilDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_detail)
        if(intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imageUrl")).into(photo_view)
        }
    }
}