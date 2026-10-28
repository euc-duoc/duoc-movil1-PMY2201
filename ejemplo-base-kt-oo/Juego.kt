open class Juego(
    val id: Int,
    val nombre: String,
    val precio: Int,
    stockInicial: Int
) {
    var stock: Int = stockInicial
        private set
    var vendidos: Long = 0
        private set
    var totalVentas: Long = 0
        private set

    init {
        require(id > 0 && nombre.isNotBlank() && precio > 0 && stockInicial >= 0)
    }

    // Regla de semana 1: 20 % al comprar más de una unidad.
    open fun porcentajeDescuento(cantidad: Int): Int = if (cantidad > 1) 20 else 0

    fun cotizar(cantidad: Int): Pedido {
        require(cantidad > 0)
        val subtotal = precio.toLong() * cantidad
        val porcentaje = porcentajeDescuento(cantidad)
        val descuento = subtotal * porcentaje / 100
        return Pedido(id, nombre, cantidad, subtotal, porcentaje, descuento, subtotal - descuento)
    }

    fun sumarStock(cantidad: Int) {
        require(cantidad > 0 && stock.toLong() + cantidad <= Int.MAX_VALUE)
        stock += cantidad
    }

    fun vender(cantidad: Int): Pedido {
        require(cantidad > 0 && cantidad <= stock)
        val pedido = cotizar(cantidad)
        stock -= cantidad
        vendidos += cantidad
        totalVentas += pedido.total
        return pedido
    }
}
