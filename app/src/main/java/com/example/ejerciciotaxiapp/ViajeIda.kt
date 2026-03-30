package com.example.ejerciciotaxiapp

import java.io.Serializable

//@Parcelize
data class ViajeIda(
    val ciudadOrigen: String,
    val ciudadDestino: String,
    val fecha: String,
    val hora: String,
    val tipoViaje: String,
    val nombre: String,
    val direccion: String,
    val dni: String
) : Serializable