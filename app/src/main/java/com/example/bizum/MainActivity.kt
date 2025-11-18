package com.example.bizum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
    // Column sirve para organizar el contenido de manera horizontal
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Función imagen para añadirla
        Image(
            painter = painterResource(R.drawable.logo_bizum), // Añadir la imagen
            contentDescription = "Logo de la aplicación", // Agregar una descripción del contenido de la imagen
            modifier = Modifier.height(150.dp).width(150.dp) // Ajustar tamaño de la imagen
        )
        // Spacer agregar un espacio entre la imagen y el botón
        Spacer(
            modifier = Modifier.height(100.dp)
        )
        // Llamada a la función para el cuadro de texto donde añadiremos los contactos
        NombreContactoField()
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        ImporteField()
        Spacer(
            modifier = Modifier.height(100.dp)
        )
        // Función botón para añadirlo
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.height(60.dp).width(120.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.rojo), // Color para el botón
                contentColor = colorResource(R.color.white)) // Color para el texto del botón
            ) {
            Text(stringResource(R.string.Enviar))
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
