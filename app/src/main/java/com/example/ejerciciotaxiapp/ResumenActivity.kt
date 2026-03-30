package com.example.ejerciciotaxiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResumenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        //Obtenemos los datos del intent
        val viajeRecibidoIda= intent.getSerializableExtra("VIAJE IDA") as? ViajeIda
        val viajeRecibidoIdaVuelta= intent.getSerializableExtra("VIAJE IDA Y VUELTA") as? ViajeIdaVuelta

        //Obtenemos las referenicas de los TextViews del layout
        val textViewTipoViaje= findViewById<TextView>(R.id.textViewTipoViaje)
        val textViewCiudadOrigen= findViewById<TextView>(R.id.textViewCiudadOrigen)
        val textViewCiudadDestino= findViewById<TextView>(R.id.textViewCiudadDestino)
        val textViewFecha= findViewById<TextView>(R.id.textViewFecha)
        val textViewHora= findViewById<TextView>(R.id.textViewHora)
        val textViewNombre= findViewById<TextView>(R.id.textViewNombre)
        val textViewDireccion= findViewById<TextView>(R.id.textViewDireccion)
        val textViewDni= findViewById<TextView>(R.id.textViewDni)
        val textViewFechaVuelta= findViewById<TextView>(R.id.textViewFechaVuelta)
        val textViewHoraVuelta= findViewById<TextView>(R.id.textViewHoraVuelta)

        //Verificar el tipo de viaje recibido y mostrar datos
        if(viajeRecibidoIda != null) {
            //Mostrar datos de ViajeIda
            textViewTipoViaje.text= "Tipo de viaje: " + viajeRecibidoIda.tipoViaje
            textViewCiudadOrigen.text= "Ciudad origen: " + viajeRecibidoIda.ciudadOrigen
            textViewCiudadDestino.text= "Ciudad destiono: " + viajeRecibidoIda.ciudadDestino
            textViewFecha.text= "Fecha: " + viajeRecibidoIda.fecha
            textViewHora.text= "Hora: " + viajeRecibidoIda.hora
            textViewNombre.text= "Nombre: " + viajeRecibidoIda.nombre
            textViewDireccion.text= "Dirección: " + viajeRecibidoIda.direccion
            textViewDni.text= "Dni: " + viajeRecibidoIda.dni
        }else if(viajeRecibidoIdaVuelta != null) {
            //Mostrar datos de ViajeIdaVuelta
            textViewTipoViaje.text= "Tipo de viaje: " + viajeRecibidoIdaVuelta.tipoViaje
            textViewCiudadOrigen.text= "Ciudad origen: " + viajeRecibidoIdaVuelta.ciudadOrigen
            textViewCiudadDestino.text= "Ciudad destino: " + viajeRecibidoIdaVuelta.ciudadDestino
            textViewFecha.text= "Fecha: " + viajeRecibidoIdaVuelta.fecha
            textViewHora.text= "Hora: " + viajeRecibidoIdaVuelta.hora
            textViewNombre.text= "Nombre: " + viajeRecibidoIdaVuelta.nombre
            textViewDireccion.text= "Dirección: " + viajeRecibidoIdaVuelta.direccion
            textViewDni.text= "Dni: " + viajeRecibidoIdaVuelta.dni
            textViewFechaVuelta.text= "Fecha vuelta: " + viajeRecibidoIdaVuelta.fechaVuelta
            textViewHoraVuelta.text= "Hora vuelta: " + viajeRecibidoIdaVuelta.horaVuelta
        }else {
            textViewTipoViaje.text= "No se recibió ningún viaje"
        }
    }
}