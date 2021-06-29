package com.example.crazyquiz.firebaseModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Casilla (
    var estatus: Int? = 1,
    var imagen: String? = "",
    var puntoPara: Int? = 0,
) {
}