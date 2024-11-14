package com.example.ejemplo_compose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ejemplo_compose.ui.theme.Ejemplo_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Ejemplo_ComposeTheme {
                EjemploCompose()
            }
        }
    }
}

@Composable
fun EjemploCompose() {
    val configActual = LocalConfiguration.current
    val isLandscape =
        configActual.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        EjemploHorizontal()
    } else {
        EjemploVertical()
    }
}

@Composable
fun EjemploVertical() {
    val teal: Color = colorResource(R.color.teal_200)
    var contador by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(teal)
            .padding(10.dp)
    ) {
        // dos filas que ocupan cada una la mitad
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(colorResource(R.color.teal_700))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Text(
                    contador.toString(),
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            contador++
                        },
                        colors = ButtonDefaults.buttonColors(Color.Magenta)
                    ) {
                        Text("Sumar")
                    }
                    Button(
                        onClick = {
                            contador--
                        },
                        colors = ButtonDefaults.buttonColors(Color.Magenta)
                    ) {
                        Text("Restar")
                    }
                    Button(
                        onClick = {
                            contador = 0
                        },
                        colors = ButtonDefaults.buttonColors(Color.DarkGray),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text("Resetear")
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(colorResource(R.color.purple_500))
        ) {

        }
    }
}

@Composable
fun EjemploHorizontal() {
    val purple: Color = colorResource(R.color.purple_200)
    var contador by rememberSaveable { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(purple)
            .padding(10.dp)
    ) {
        // dos filas que ocupan cada una la mitad
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(colorResource(R.color.teal_700))
        ) {
            Text(
                contador.toString(),
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(colorResource(R.color.purple_500))
        ) {

        }
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Composable
fun PreviewVertical() {
    Ejemplo_ComposeTheme {
        EjemploVertical()
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 400)
@Composable
fun PreviewHorizontal() {
    Ejemplo_ComposeTheme {
        EjemploHorizontal()
    }
}