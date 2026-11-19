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
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel

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
fun AppLogin(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = viewModel()
) {
    val estado by loginViewModel.estado.observeAsState(EstadoLogin())
    val usuario = estado.usuarioActivo

    if (usuario == null) {
        PantallaLogin(
            alIntentarIngreso = loginViewModel::ingresar,
            modifier = modifier
        )
    } else {
        PantallaBienvenida(
            usuario = usuario,
            totalIngresos = estado.totalIngresos,
            alVolver = loginViewModel::volverAlLogin,
            modifier = modifier
        )
    }
}