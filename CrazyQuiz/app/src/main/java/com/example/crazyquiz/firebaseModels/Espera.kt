package com.example.crazyquiz.firebaseModels

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Espera (
    var lista: MutableList<MutableMap<String, Any>> = mutableListOf<MutableMap<String, Any>>(),
) {
}