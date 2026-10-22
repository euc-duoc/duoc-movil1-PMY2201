fun agregarJuego(juegos: MutableList<Juego>) {
    println("\n--- AGREGAR JUEGO ---")

    // Solicitar nombre
    print("Nombre del juego: ")
    val nombre = readlnOrNull() ?: ""

    // Solicitar precio
    print("Precio del juego: ")
    val precio = readlnOrNull()?.toIntOrNull() ?: 0

    // Solicitar stock
    print("Stock inicial: ")
    val stock = readlnOrNull()?.toIntOrNull() ?: 0

    // Generar identificador automáticamente
    val id = (juegos.maxOfOrNull { it.id } ?: 0) + 1
    
    if (nombre.isNotBlank() && precio > 0 && stock >= 0) {
        // Crear y agregar el juego
        val juego = Juego(id, nombre, precio, stock)
        juegos.add(juego)
        println("Juego agregado correctamente.")
        println(juego)
    } else {
        println("Error: los datos ingresados no son válidos.")
    }    
}

fun sumarStock(juegos: List<Juego>) {
     println("\n--- SUMAR STOCK ---")

    // Mostrar juegos disponibles
    for (juego in juegos) {
        println("${juego.id}. ${juego.nombre} - Stock: ${juego.stock}")
    }

    // Solicitar identificador
    print("\nIngrese el ID del juego: ")
    val id = readlnOrNull()?.toIntOrNull() ?: 0

    // Buscar el juego
    val juego = juegos.find { it.id == id }

    if (juego != null) {
        println("Juego seleccionado: ${juego.nombre}")
        println("Stock actual: ${juego.stock}")

        // Solicitar cantidad
        print("Cantidad de unidades a agregar: ")
        val cantidad = readlnOrNull()?.toIntOrNull() ?: 0

        if (cantidad > 0) {
            juego.stock += cantidad

            println("\nStock actualizado correctamente.")
            println("Nuevo stock de ${juego.nombre}: ${juego.stock}")
        } else {
            println("Error: la cantidad debe ser mayor que cero.")
        }

    } else {
        println("Error: no existe un juego con el ID ingresado.")
    }
}

fun realizarPedido(juegos: List<Juego>) {
    println("\n--- REALIZAR PEDIDO ---")

    // Mostrar juegos disponibles
    for (juego in juegos) {
        println("${juego.id}. ${juego.nombre} - Precio: $${juego.precio} - Stock: ${juego.stock}")
    }

    // Solicitar identificador
    print("\nIngrese el ID del juego: ")
    val id = readlnOrNull()?.toIntOrNull() ?: 0

    // Buscar el juego
    val juego = juegos.find { it.id == id }

    if (juego != null) {

        println("Juego seleccionado: ${juego.nombre}")
        println("Precio unitario: $${juego.precio}")

        // Solicitar cantidad
        print("Cantidad de unidades a comprar: ")
        val cantidad = readlnOrNull()?.toIntOrNull() ?: 0

        // Verificar cantidad y disponibilidad
        if (cantidad <= 0) {
            println("Error: la cantidad debe ser mayor que cero.")

        } else if (cantidad > juego.stock) {
            println("Error: stock insuficiente.")
            println("Stock disponible: ${juego.stock}")

        } else {

            // Calcular subtotal
            val subtotal = juego.precio * cantidad

            // Aplicar descuento si compra más de una unidad
            val descuento = if (cantidad > 1) subtotal * 20 / 100 else 0

            val total = subtotal - descuento

            // Mostrar resumen
            println("\n--- RESUMEN DEL PEDIDO ---")
            println("Juego: ${juego.nombre}")
            println("Cantidad: $cantidad")
            println("Subtotal: $$subtotal")
            println("Descuento: $$descuento")
            println("Total a pagar: $$total")

            // Confirmar compra
            print("\n¿Confirmar pedido? (s/n): ")
            val confirmar = readlnOrNull()

            if (confirmar == "s") {

                // Actualizar inventario y ventas
                juego.stock -= cantidad
                juego.vendidos += cantidad
                juego.totalVentas += total

                println("\nPedido realizado correctamente.")
                println("Stock restante: ${juego.stock}")

            } else {
                println("Pedido cancelado.")
            }
        }

    } else {
        println("Error: no existe un juego con el ID ingresado.")
    }
}

fun mostrarEstadisticas(juegos: List<Juego>) {
    println("\n--- ESTADÍSTICAS DE VENTAS ---")

    var unidadesTotales = 0
    var ventasTotales = 0

    // Recorrer los juegos
    for (juego in juegos) {
        println("\nJuego: ${juego.nombre}")
        println("Unidades vendidas: ${juego.vendidos}")
        println("Total recaudado: $${juego.totalVentas}")

        // Acumular estadísticas generales
        unidadesTotales += juego.vendidos
        ventasTotales += juego.totalVentas
    }

    // Mostrar estadísticas generales
    println("\n--- RESUMEN GENERAL ---")
    println("Total de unidades vendidas: $unidadesTotales")
    println("Total recaudado: $$ventasTotales")
}