interface OperacionesTienda {
    fun listarJuegos(): List<FichaJuego>
    fun agregarJuego(nombre: String, precio: Int, stock: Int, descuento: Int = 0): Resultado<FichaJuego>
    fun sumarStock(id: Int, cantidad: Int): Resultado<FichaJuego>
    fun cotizarPedido(id: Int, cantidad: Int): Resultado<Pedido>
    fun confirmarPedido(id: Int, cantidad: Int): Resultado<Pedido>
    fun obtenerEstadisticas(): Estadisticas
}
