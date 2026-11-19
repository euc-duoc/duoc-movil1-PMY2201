package com.example.loginsimple

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun PantallaLogin(
    alIntentarIngreso: (String, String) -> Boolean,
    modifier: Modifier = Modifier
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = usuario,
            onValueChange = {
                usuario = it
                mensajeError = null
            },
            label = { Text("Usuario") },
            singleLine = true
        )

        OutlinedTextField(
            value = contrasena,
            onValueChange = {
                contrasena = it
                mensajeError = null
            },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )

        mensajeError?.let { mensaje ->
            Text(mensaje, color = MaterialTheme.colorScheme.error)
        }

        Button(onClick = {
            val nombreUsuario = usuario.trim()

            mensajeError = when {
                nombreUsuario.isBlank() || contrasena.isBlank() ->
                    "Completa ambos campos."

                alIntentarIngreso(nombreUsuario, contrasena) ->
                    null

                else ->
                    "Usuario o contraseña incorrectos."
            }
        }) {
            Text("Ingresar")
        }
    }
}