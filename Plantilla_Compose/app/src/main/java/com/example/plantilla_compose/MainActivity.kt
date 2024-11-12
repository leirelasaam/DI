package com.example.plantilla_compose

import android.content.res.Configuration
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.plantilla_compose.ui.theme.Plantilla_ComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Plantilla_ComposeTheme {
                PlantillaComposeMain()
            }
        }
    }
}

@Composable
fun PlantillaComposeMain() {
    val configuration = LocalConfiguration.current

    // Controlar la orientación
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    PlantillaCompose(isLandscape)
}

@Composable
fun PlantillaCompose(isLandscape: Boolean) {
    // ORIENTACIÓN
    // Hacer cosas dependiendo de la orientación
    val bgColor = if (isLandscape) Color.DarkGray else Color.Black

    // Obtener un String del xml
    val nombreApp = stringResource(R.string.app_name)

    // GUARDAR EL ESTADO DE LA VISTA
    // Se puede guardar así y recuperar el valor de forma normal
    var texto by rememberSaveable { mutableStateOf(nombreApp) }
    // O de esta forma, pero hay que tener cuidado al recuperar el valor, que se hace esMarciano.value
    var esMarciano : MutableState<Boolean> = rememberSaveable { mutableStateOf(true) }

    val textBtn1 = "Btn1"
    val textBtn2 = "Btn2"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Text(
            text = texto,
            color = Color.White,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 20.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    texto = textBtn1
                },
                colors = ButtonDefaults.buttonColors(Color.Yellow, Color.Black),
                modifier = Modifier.width(200.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(textBtn1)
            }
            Button(
                onClick = {
                    texto = textBtn2
                },
                colors = ButtonDefaults.buttonColors(Color.Blue, Color.White)
            ) {
                Text(textBtn2)
            }

        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    // Obtener un Drawable del xml
                    painterResource(if (esMarciano.value) R.drawable.ic_launcher_foreground else R.drawable.ic_launcher_background),
                    "img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, RoundedCornerShape(20.dp))
                        .height(100.dp)
                        .clickable {
                            esMarciano.value = !esMarciano.value
                        },
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    "Haz click en la img",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val lista = listOf("a", "b", "c")
            // Obtener un Color del xml
            val color = colorResource(R.color.purple_200)
            for (letra in lista) {
                Button(
                    onClick = {
                        texto = letra
                    },
                    colors = ButtonDefaults.buttonColors(color, Color.White)
                ) {
                    Text(letra)
                }
            }
        }
    }
}

// Poner 2 preview, uno para vertical y otro para horizontal
@Preview(showBackground = true, widthDp = 400, heightDp = 800)
@Preview(showBackground = true, widthDp = 800, heightDp = 400)
@Composable
fun PlantillaComposePreview() {
    Plantilla_ComposeTheme {
        PlantillaComposeMain()
    }
}