package com.example.loginsimple

class ValidadorLogin {
    private val usuarios = mapOf(
        "ana" to "1234",
        "pedro" to "5678",
        "camila" to "9012"
    )

    fun validar(nombreUsuario: String, contrasena: String): Boolean {
        val contrasenaRegistrada = usuarios[nombreUsuario]
        return contrasenaRegistrada == contrasena
    }
}