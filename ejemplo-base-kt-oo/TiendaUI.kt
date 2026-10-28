class TiendaUI {
    fun mostrarMensaje(mensaje: String) = println(mensaje)

    fun mostrarBienvenida(juegos: List<FichaJuego>) {
        println("===== BIENVENIDO A LA TIENDA DE JUEGOS DE MESA =====")
        mostrarJuegos(juegos)
    }

    fun leerOpcion(): Int? {
        println("\n1. Agregar un juego\n2. Sumar stock a un juego\n3. Realizar pedido de juegos\n4. Mostrar estadísticas de ventas\n0. Salir")
        return leerEntero("Seleccione una opción: ")
    }

    // null significa fin de entrada; el controlador termina o cancela la operación.
    fun leerTexto(pregunta: String): String? {
        print(pregunta)
        return readlnOrNull()
    }

    fun leerEntero(pregunta: String): Int? {
        while (true) {
            val texto = leerTexto(pregunta) ?: return null
            val numero = texto.trim().toIntOrNull()
            if (numero != null) return numero
            println("Ingrese un número entero válido.")
        }
    }

    fun confirmarPedido(): Boolean = leerTexto("¿Confirmar pedido? (s/n): ")?.trim()?.lowercase() == "s"

    fun mostrarJuegos(juegos: List<FichaJuego>) {
        println("\n--- JUEGOS REGISTRADOS ---")
        if (juegos.isEmpty()) println("No hay juegos registrados.")
        for (juego in juegos) mostrarJuego(juego)
    }

    fun mostrarJuego(juego: FichaJuego) {
        println("${juego.id}: ${juego.nombre} - $${juego.precio} (stock: ${juego.stock}, descuento para 1 unidad: ${juego.descuentoUnaUnidad} %)")
    }

    fun mostrarPedido(pedido: Pedido) {
        println("\n--- RESUMEN DEL PEDIDO ---")
        println("Juego: ${pedido.nombre}\nCantidad: ${pedido.cantidad}")
        println("Subtotal: $${pedido.subtotal}\nDescuento (${pedido.porcentaje} %): $${pedido.descuento}\nTotal a pagar: $${pedido.total}")
    }

    fun mostrarError(error: ErrorOperacion) {
        val mensaje = when (error) {
            ErrorOperacion.DATOS_INVALIDOS -> "Nombre obligatorio, precio positivo, stock no negativo y descuento entre 0 y 100."
            ErrorOperacion.JUEGO_NO_EXISTE -> "No existe un juego con el ID ingresado."
            ErrorOperacion.CANTIDAD_INVALIDA -> "La cantidad debe ser mayor que cero."
            ErrorOperacion.STOCK_INSUFICIENTE -> "Stock insuficiente. Consulte las unidades disponibles en el catálogo."
            ErrorOperacion.LIMITE_STOCK -> "La cantidad supera el límite de stock permitido."
        }
        println("Error: $mensaje")
    }

    fun mostrarEstadisticas(estadisticas: Estadisticas) {
        println("\n--- ESTADÍSTICAS DE VENTAS ---")
        for (juego in estadisticas.juegos) {
            println("${juego.nombre}: ${juego.vendidos} unidades vendidas; recaudado: $${juego.totalVentas}")
        }
        println("\n--- RESUMEN GENERAL ---")
        println("Total de unidades vendidas: ${estadisticas.unidadesTotales}")
        println("Total recaudado: $${estadisticas.ventasTotales}")
    }
}
