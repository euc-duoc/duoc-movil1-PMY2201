package com.example.loginsimple

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import com.example.loginsimple.ui.theme.LoginSimpleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginSimpleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppLogin(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppLogin(modifier: Modifier = Modifier) {
    val validador = remember { ValidadorLogin() }
    var usuarioActivo by remember { mutableStateOf<String?>(null) }
    var totalIngresos by remember { mutableStateOf(0) }

    val usuario = usuarioActivo

    if (usuario == null) {
        PantallaLogin(
            validar = validador::validar,
            alIngresar = { nombre ->
                totalIngresos++
                usuarioActivo = nombre
            },
            modifier = modifier
        )
    } else {
        PantallaBienvenida(
            usuario = usuario,
            totalIngresos = totalIngresos,
            alVolver = { usuarioActivo = null },
            modifier = modifier
        )
    }
}