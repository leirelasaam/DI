package com.example.plantilla_xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var contador: Int = 0
    private var esMarciano = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // APARTADO CONTADOR
        findViewById<TextView>(R.id.textView).text = contador.toString()

        val  btnSumar = findViewById<Button>(R.id.btnSumar)
        val  btnRestar = findViewById<Button>(R.id.btnRestar)
        val btnResetear = findViewById<Button>(R.id.btnResetear)

        val botones = listOf(btnResetear, btnRestar, btnSumar)
        for (boton in botones){
            boton.setOnClickListener(){
                val texto = boton.text.toString()
                gestionarContador(texto)
            }
        }

        // APARTADO IMAGEN
        val img = findViewById<ImageView>(R.id.imageView)
        val btnCambiar = findViewById<Button>(R.id.btnCambiar)
        val msg = findViewById<TextView>(R.id.textViewMsg)

        btnCambiar.setOnClickListener(){
            // Set inline si solo hay que hacer cambio del resource
            //img.setImageResource(if (esMarciano) R.drawable.ic_launcher_background else R.drawable.ic_launcher_foreground)

            // Set con if explícito

            if (esMarciano){
                msg.text = getString(R.string.adios)
                img.setImageResource(R.drawable.ic_launcher_background)
            } else {
                msg.text = getString(R.string.hola)
                img.setImageResource(R.drawable.ic_launcher_foreground)
            }


            esMarciano = !esMarciano
        }
    }

    private fun gestionarContador(text: String){
        when(text){
            "Sumar" -> contador++
            "Restar" -> contador--
            else -> contador = 0
        }

        findViewById<TextView>(R.id.textView).text = contador.toString()
    }
}