// Objetos de datos: transportan resultados, no realizan entrada/salida.
data class FichaJuego(
    val id: Int,
    val nombre: String,
    val precio: Int,
    val stock: Int,
    val vendidos: Long,
    val totalVentas: Long,
    val descuentoUnaUnidad: Int
)

data class Pedido(
    val idJuego: Int,
    val nombre: String,
    val cantidad: Int,
    val subtotal: Long,
    val porcentaje: Int,
    val descuento: Long,
    val total: Long
)

data class Estadisticas(
    val juegos: List<FichaJuego>,
    val unidadesTotales: Long,
    val ventasTotales: Long
)

enum class ErrorOperacion {
    DATOS_INVALIDOS, JUEGO_NO_EXISTE, CANTIDAD_INVALIDA, STOCK_INSUFICIENTE, LIMITE_STOCK
}

// El backend entrega un dato o un error. La UI decide cómo presentarlo.
class Resultado<T> private constructor(val dato: T?, val error: ErrorOperacion?) {
    companion object {
        fun <T> exito(dato: T): Resultado<T> = Resultado(dato, null)
        fun <T> fallo(error: ErrorOperacion): Resultado<T> = Resultado(null, error)
    }
}
