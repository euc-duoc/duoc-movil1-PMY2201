class JuegoConDescuento(
    id: Int,
    nombre: String,
    precio: Int,
    stockInicial: Int,
    val descuentoPersonalizado: Int
) : Juego(id, nombre, precio, stockInicial) {
    init {
        require(descuentoPersonalizado in 1..100)
    }

    override fun porcentajeDescuento(cantidad: Int): Int =
        maxOf(super.porcentajeDescuento(cantidad), descuentoPersonalizado)
}
