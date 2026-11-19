package com.example.loginsimple

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class EstadoLogin(
    val usuarioActivo: String? = null,
    val totalIngresos: Int = 0
)

class LoginViewModel : ViewModel() {

    private val validador = ValidadorLogin()

    private val _estado = MutableLiveData(EstadoLogin())
    val estado: LiveData<EstadoLogin> = _estado

    fun ingresar(usuario: String, contrasena: String): Boolean {
        if (!validador.validar(usuario, contrasena)) {
            return false
        }

        val estadoActual = _estado.value ?: EstadoLogin()

        _estado.value = estadoActual.copy(
            usuarioActivo = usuario,
            totalIngresos = estadoActual.totalIngresos + 1
        )

        return true
    }

    fun volverAlLogin() {
        val estadoActual = _estado.value ?: EstadoLogin()
        _estado.value = estadoActual.copy(usuarioActivo = null)
    }
}