class TiendaControlador(private val operaciones: OperacionesTienda, private val ui: TiendaUI) {
    fun iniciar() {
        ui.mostrarBienvenida(operaciones.listarJuegos())
        while (true) {
            when (ui.leerOpcion()) {
                1 -> agregarJuego()
                2 -> sumarStock()
                3 -> realizarPedido()
                4 -> ui.mostrarEstadisticas(operaciones.obtenerEstadisticas())
                0, null -> {
                    ui.mostrarMensaje("Saliendo del sistema...")
                    return
                }
                else -> ui.mostrarMensaje("Opción no válida.")
            }
        }
    }

    private fun agregarJuego() {
        ui.mostrarMensaje("\n--- AGREGAR JUEGO ---")
        val nombre = ui.leerTexto("Nombre: ") ?: return
        val precio = ui.leerEntero("Precio: ") ?: return
        val stock = ui.leerEntero("Stock inicial: ") ?: return
        val descuento = ui.leerEntero("Descuento personalizado (0 = estándar, 1 a 100 = con descuento): ") ?: return
        val resultado = operaciones.agregarJuego(nombre, precio, stock, descuento)
        if (resultado.error != null) ui.mostrarError(resultado.error)
        resultado.dato?.let {
            ui.mostrarMensaje("Juego agregado correctamente.")
            ui.mostrarJuego(it)
        }
    }

    private fun sumarStock() {
        ui.mostrarJuegos(operaciones.listarJuegos())
        val id = ui.leerEntero("ID del juego: ") ?: return
        val cantidad = ui.leerEntero("Cantidad de unidades a agregar: ") ?: return
        val resultado = operaciones.sumarStock(id, cantidad)
        if (resultado.error != null) ui.mostrarError(resultado.error)
        resultado.dato?.let {
            ui.mostrarMensaje("Stock actualizado correctamente.")
            ui.mostrarJuego(it)
        }
    }

    private fun realizarPedido() {
        ui.mostrarJuegos(operaciones.listarJuegos())
        val id = ui.leerEntero("ID del juego: ") ?: return
        val cantidad = ui.leerEntero("Cantidad de unidades a comprar: ") ?: return
        val cotizacion = operaciones.cotizarPedido(id, cantidad)
        if (cotizacion.error != null) {
            ui.mostrarError(cotizacion.error)
            return
        }
        val pedido = cotizacion.dato ?: return
        ui.mostrarPedido(pedido)
        if (!ui.confirmarPedido()) {
            ui.mostrarMensaje("Pedido cancelado.")
            return
        }
        val resultado = operaciones.confirmarPedido(id, cantidad)
        if (resultado.error != null) ui.mostrarError(resultado.error)
        resultado.dato?.let {
            ui.mostrarMensaje("Pedido realizado correctamente.")
            ui.mostrarJuegos(operaciones.listarJuegos().filter { juego -> juego.id == id })
        }
    }
}
