data class Juego(
    val id: Int,
    val nombre: String,
    val precio: Int,
    var stock: Int,
    var vendidos: Int = 0,
    var totalVentas: Int = 0
)