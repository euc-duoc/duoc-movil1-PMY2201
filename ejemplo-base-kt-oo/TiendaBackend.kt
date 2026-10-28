class TiendaBackend(juegosIniciales: List<Juego>) : OperacionesTienda {
    private val juegos = juegosIniciales.toMutableList()

    init {
        require(juegos.map { it.id }.distinct().size == juegos.size)
    }

    private fun ficha(juego: Juego) = FichaJuego(
        juego.id, juego.nombre, juego.precio, juego.stock,
        juego.vendidos, juego.totalVentas, juego.porcentajeDescuento(1)
    )

    override fun listarJuegos(): List<FichaJuego> = juegos.map { ficha(it) }

    override fun agregarJuego(nombre: String, precio: Int, stock: Int, descuento: Int): Resultado<FichaJuego> {
        val ultimoId = juegos.maxOfOrNull { it.id } ?: 0
        if (nombre.isBlank() || precio <= 0 || stock < 0 || descuento !in 0..100 || ultimoId == Int.MAX_VALUE) {
            return Resultado.fallo(ErrorOperacion.DATOS_INVALIDOS)
        }
        val id = ultimoId + 1
        val juego = if (descuento == 0) Juego(id, nombre.trim(), precio, stock)
        else JuegoConDescuento(id, nombre.trim(), precio, stock, descuento)
        juegos.add(juego)
        return Resultado.exito(ficha(juego))
    }

    override fun sumarStock(id: Int, cantidad: Int): Resultado<FichaJuego> {
        val juego = juegos.find { it.id == id }
            ?: return Resultado.fallo(ErrorOperacion.JUEGO_NO_EXISTE)
        if (cantidad <= 0) return Resultado.fallo(ErrorOperacion.CANTIDAD_INVALIDA)
        if (juego.stock.toLong() + cantidad > Int.MAX_VALUE) return Resultado.fallo(ErrorOperacion.LIMITE_STOCK)
        juego.sumarStock(cantidad)
        return Resultado.exito(ficha(juego))
    }

    override fun cotizarPedido(id: Int, cantidad: Int): Resultado<Pedido> {
        val juego = juegos.find { it.id == id }
            ?: return Resultado.fallo(ErrorOperacion.JUEGO_NO_EXISTE)
        if (cantidad <= 0) return Resultado.fallo(ErrorOperacion.CANTIDAD_INVALIDA)
        if (cantidad > juego.stock) return Resultado.fallo(ErrorOperacion.STOCK_INSUFICIENTE)
        // Llamada polimórfica: cada juego conoce su descuento.
        return Resultado.exito(juego.cotizar(cantidad))
    }

    override fun confirmarPedido(id: Int, cantidad: Int): Resultado<Pedido> {
        val validacion = cotizarPedido(id, cantidad)
        if (validacion.error != null) return Resultado.fallo(validacion.error)
        val juego = juegos.first { it.id == id }
        return Resultado.exito(juego.vender(cantidad))
    }

    override fun obtenerEstadisticas(): Estadisticas {
        val fichas = listarJuegos()
        return Estadisticas(fichas, fichas.sumOf { it.vendidos }, fichas.sumOf { it.totalVentas })
    }
}
