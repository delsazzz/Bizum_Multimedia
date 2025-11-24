package com.example.bizum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizum.ui.theme.BizumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizumTheme {
                BizumApp()
                }
            }
        }
    }

@Preview
@Composable
fun BizumApp() {
    PantallaPrincipalBizum(modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center))
}

@Preview(showBackground = true)
@Composable
fun PantallaPrincipalBizum (modifier: Modifier = Modifier) {
    var enviado by remember { mutableStateOf(false) } // Declaramos la variable enviado para usarla en el botón
    // Abrimos esta box para meter todo el contenido dentro
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Esta imagen es el fondo de la pantalla y por eso la colocamos arriba del todo
        Image(
            painter = painterResource(R.drawable.fondo_bizum),
            contentDescription = null, // Necesario aunque sea null para que funcione Image()
            contentScale = ContentScale.Crop, // Esto hace que ocupe toda la pantalla
            modifier = Modifier.fillMaxSize()
        )

    // Column sirve para organizar el contenido de manera horizontal
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Función imagen para añadirla
        Image(
            painter = painterResource(R.drawable.logo_bizum_rjd_sin_fondo), // Añadir la imagen
            contentDescription = "Logo de la aplicación", // Agregar una descripción del contenido de la imagen
            modifier = Modifier.height(150.dp).width(150.dp) // Ajustar tamaño de la imagen
        )
        // Spacer agregar un espacio entre la imagen y el botón
        Spacer(
            modifier = Modifier.height(50.dp)
        )
        // Llamada a la función para el cuadro de texto donde añadiremos los contactos
        NombreContactoField()
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        ImporteField()
        Spacer(
            modifier = Modifier.height(50.dp)
        )
        // Función botón para añadirlo
        Button(
            onClick = { enviado = true }, // Dentro del onClick definimos que funcione cuando la variable sea true
            modifier = Modifier.height(60.dp).width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.rojo), // Color para el botón
                contentColor = colorResource(R.color.white) // Color para el texto del botón
            )
        ) {
            Text(stringResource(R.string.Enviar))
        }
        // Este bucle ejecuta el texto si pulsamos el botón porque enviado está definido como true
        if (enviado) {
            Spacer(
                modifier = Modifier.height(100.dp)
            )
            // Texto que aparece cuando pulsamos el botón de enviar
            Text(
                text = "¡Bizum enviado!",
                color = colorResource(R.color.verde)
            )
        }
    }

        }
    }
@Composable
// Creamos una función que será el cuadro de texto donde añadamos los contactos
fun NombreContactoField(){
    var nombre by remember { mutableStateOf("") }
    TextField(
        value = nombre,
        onValueChange = {nombre = it},
        label = {Text("Nombre del contacto")},
        placeholder = {Text("Seleccionar contacto...")},
        singleLine = true

    )
}


@Composable
// Creamos otra función para el campo de texto del importe que queramos enviar
fun ImporteField(){
    var importe by remember { mutableStateOf("") }
    TextField(
        value = importe,
        onValueChange = {importe = it},
        label = {Text("Importe")},
        placeholder = {Text("0,00€")},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) // Esto permite que el teclado que aparezca sea solo numérico
    )
}
