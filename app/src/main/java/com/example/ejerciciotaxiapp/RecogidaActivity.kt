package com.example.ejerciciotaxiapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import java.util.Calendar

class RecogidaActivity : AppCompatActivity() {

    //Variables para almacenar los datos recopilados
    var seleccionFecha: String= ""
    var seleccionHora: String= ""
    var seleccionCiudadOrigen: String= ""
    var seleccionCiudadDestino: String= ""
    var seleccionTipoViaje: String=""
    var seleccionNombre: String=""
    var seleccionDireccion: String=""
    var seleccionDni: String=""
    var seleccionFechaVuelta: String= ""
    var seleccionHoraVuelta : String= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recogida)

        val calendario= Calendar.getInstance()

        //Inicializamos los elementos de la interfaz
        val ciudadesOrigen= findViewById<Spinner>(R.id.spinnerCiudadOrigen)
        val ciudadesDestino= findViewById<Spinner>(R.id.spinnerCiudades)
        val opcionesCiudades= findViewById<RadioGroup>(R.id.opciones)
        val opcionIda= findViewById<RadioButton>(R.id.ida)
        val opcionIdaVuelta= findViewById<RadioButton>(R.id.idaYvuelta)
        val nombre= findViewById<EditText>(R.id.editTextNombre)
        val direccion= findViewById<EditText>(R.id.editTextDirección)
        val dni= findViewById<EditText>(R.id.editTextDni)
        val fechaIda= findViewById<EditText>(R.id.editTextFecha)
        val horaIda= findViewById<EditText>(R.id.editTextHora)
        val boton= findViewById<Button>(R.id.button)

        //Declarar una variable para almacenar la ciudad de origen
        //En onCreate, configurar un listener para el Spinner de ciudad de origen
        ciudadesOrigen.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                seleccionCiudadOrigen= ciudadesOrigen.selectedItem.toString()
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                //No se requiere acción aquí
            }
        }

        //Configuramos un listener para el botón de selección de ciudad
        ciudadesDestino.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long){
                seleccionCiudadDestino= ciudadesDestino.selectedItem.toString()
            }
            override fun onNothingSelected(parentView: AdapterView<*>?) {
                //No se requiere acción aquí
                println("NO SE HA SELECCIONADO NINGUNA CIUDAD")
            }
        }

        //Configuramos un listener para el botón de fecha
        fechaIda.setOnClickListener {
            val calendar= Calendar.getInstance()
            val year= calendar.get(Calendar.YEAR)
            val month= calendar.get(Calendar.MONTH)
            val dayOfMonth= calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog= DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    seleccionFecha= "$dayOfMonth/${month + 1}/$year"
                    fechaIda.setText(seleccionFecha)
                }, year, month, dayOfMonth)
            datePickerDialog.show()
        }
        //Configuramos un listener para el botón de hora
        horaIda.setOnClickListener {
            val calendar= Calendar.getInstance()
            val hora= calendar.get(Calendar.HOUR_OF_DAY)
            val minutos= calendar.get(Calendar.MINUTE)

            val timePickerDialog= TimePickerDialog(this,
                {
                        view, hora, minutos ->
                    seleccionHora="$hora:$minutos"
                    horaIda.setText(seleccionHora)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true).show()
        }

        //Configuramos un listener para los RadioButtons
        opcionesCiudades.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.ida -> {
                    seleccionTipoViaje= "solo ida"
                }//Fin opción ida

                R.id.idaYvuelta -> {
                    seleccionTipoViaje= "ida y vuelta"

                    //Configuramos un listener para el botón de fecha de vuelta
                    val fechaVueltaEditText= findViewById<EditText>(R.id.editTextFechaVuelta)
                    fechaVueltaEditText.visibility= View.VISIBLE

                    fechaVueltaEditText.setOnClickListener {
                        val calendar= Calendar.getInstance()
                        val year= calendar.get(Calendar.YEAR)
                        val month= calendar.get(Calendar.MONTH)
                        val dayOfMonth= calendar.get(Calendar.DAY_OF_MONTH)

                        val datePickerDialog= DatePickerDialog(this,
                            DatePickerDialog.OnDateSetListener { _, anyo, mes, dia ->
                                seleccionFechaVuelta= "$dia/${mes + 1}/$anyo"
                                fechaVueltaEditText.setText(seleccionFechaVuelta)
                            }, year, month, dayOfMonth)
                        datePickerDialog.show()
                    }

                    //Configuramos un listener para el botón de hora de vuelta
                    val horaVueltaEditText= findViewById<EditText>(R.id.editTextHoraVuelta)
                    horaVueltaEditText.visibility= View.VISIBLE

                    horaVueltaEditText.setOnClickListener {
                        val calendar= Calendar.getInstance()
                        val hora= calendar.get(Calendar.HOUR_OF_DAY)
                        val minutos= calendar.get(Calendar.MINUTE)

                        val timePickerDialog= TimePickerDialog(this,
                            { _, hora, minutos ->
                                seleccionHoraVuelta= "$hora:$minutos"
                                horaVueltaEditText.setText(seleccionHoraVuelta)
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true).show()
                    }
                }//Fin opción idaYvuelta
            }//Fin when
        }//Fin setOnCheckedChangeListener

        //Configuramos un listener para el campo de nombre
        nombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                seleccionNombre= s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No hace falta implementar nada en este caso
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No hace falta implementar nada en este caso
            }
        })

        //Configuramos un listener para el campo de direccion
        direccion.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                seleccionDireccion= s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No hace falta implementar nada en este caso
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No hace falta implementar nada en este caso
            }
        })

        //Configuramos un listener para el campo de dni
        dni.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                seleccionDni= s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //No hace falta implementar nada en este caso
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //No hace falta implementar nada en este caso
            }
        })

        //Configuramos un listener para el botón y finalizar
        boton.setOnClickListener(View.OnClickListener {
            //Verificamos que al menos una de las 2 opciones de viaje esté seleccionada
            if(!opcionIda.isChecked && !opcionIdaVuelta.isChecked) {
                Toast.makeText(this, "POR FAVOR, SELECCIONE UN TIPO DE VIAJE (Y DESPUÉS COMPLETE LOS CAMPOS)", Toast.LENGTH_SHORT).show()
            }else if(opcionIda.isChecked || opcionIdaVuelta.isChecked){
                //Validamos que los datos se han completado
                if(seleccionCiudadOrigen.isEmpty() || seleccionCiudadDestino.isEmpty() || seleccionFecha.isEmpty()
                    || seleccionHora.isEmpty() || seleccionNombre.isEmpty() || seleccionDireccion.isEmpty() ||
                    seleccionDni.isEmpty()) {
                    Toast.makeText(this, "POR FAVOR, COMPLETE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                }else if(seleccionCiudadOrigen == seleccionCiudadDestino) {
                    Toast.makeText(this,"LA CIUDAD ORIGEN Y DESTINO DEBEN SER DISTINTAS", Toast.LENGTH_SHORT).show()
                }else if(!seleccionDni.matches(Regex("[0-9]{8}[A-Z]"))) {
                    //Validamos el formato del DNI
                    Toast.makeText(this, "EL FORMATO DEL DNI ES INCORRECTO. DEBE SER 8 DÍGITOS Y UNA LETRA (EJEMPLO: 12345678A)",
                        Toast.LENGTH_SHORT).show()
                }else {
                        if(opcionIda.isChecked){
                            //Creamos el objeto ViajeIda
                            val ViajeIda= ViajeIda(seleccionCiudadOrigen, seleccionCiudadDestino, seleccionFecha,
                                seleccionHora, seleccionTipoViaje, seleccionNombre, seleccionDireccion, seleccionDni)
                            Toast.makeText(this, "SE HA CREADO EL VIAJE (IDA)", Toast.LENGTH_SHORT).show()
                            val intent= Intent(this, ResumenActivity::class.java)
                            intent.putExtra("VIAJE IDA", ViajeIda)
//                            intent.putExtra("TIPO_VIAJE", "IDA")
                            startActivity(intent)
                        }
                        //Si es "ida y vuelta"
                        else if(opcionIdaVuelta.isChecked){
                            if(seleccionFechaVuelta.isEmpty() || seleccionHoraVuelta.isEmpty()){
                                Toast.makeText(this, "POR FAVOR, COMPLETE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
                            }else if(seleccionFechaVuelta < seleccionFecha) {
                                Toast.makeText(this, "LA FECHA DE VUELTA NO PUEDE SER ANTERIOR A LA FECHA DE IDA", Toast.LENGTH_SHORT).show()
                            }else {
                                //Creamos el objeto ViajeIdaVuelta
                                val ViajeIdaVuelta= ViajeIdaVuelta(seleccionCiudadOrigen, seleccionCiudadDestino, seleccionFecha,
                                    seleccionHora, seleccionTipoViaje, seleccionNombre, seleccionDireccion, seleccionDni,
                                    seleccionFechaVuelta, seleccionHoraVuelta)
                                Toast.makeText(this, "SE HA CREADO EL VIAJE (IDA Y VUELTA)", Toast.LENGTH_SHORT).show()
                                val intent= Intent(this, ResumenActivity::class.java)
                                intent.putExtra("VIAJE IDA Y VUELTA", ViajeIdaVuelta)
                                startActivity(intent)
                            }
                        }
                }
            }else {
                Toast.makeText(this, "POR FAVOR, COMPLETE TODOS LOS CAMPOS", Toast.LENGTH_SHORT).show()
            }
        })

    }
}