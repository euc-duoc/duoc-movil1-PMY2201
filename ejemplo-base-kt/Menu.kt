fun iniciar(juegos: List<Juego>) {
    println("==============================================")
    println("== BIENVENIDO A LA TIENDA DE JUEGOS DE MESA ==")
    println("==============================================\n")
    
    println("Juegos registrados en el sistema:\n")
    
    for (juego in juegos) {
        println("${juego.id}: ${juego.nombre} - $${juego.precio} (stock: ${juego.stock})")
    }
}

fun activarMenu(juegos: MutableList<Juego>) {
    var continuar = true

    while (continuar) {

        println("\n===== TIENDA DE JUEGOS DE MESA =====")
        println("1. Agregar un juego")
        println("2. Sumar stock a un juego")
        println("3. Realizar pedido de juegos")
        println("4. Mostrar estadísticas de ventas")
        println("0. Salir")
        print("\nSeleccione una opción: ")

        val opcion = readlnOrNull()?.toIntOrNull()

        when (opcion) {
            1 -> agregarJuego(juegos)
            2 -> sumarStock(juegos)
            3 -> realizarPedido(juegos)
            4 -> mostrarEstadisticas(juegos)
            0 -> {
                println("Saliendo del sistema...")
                continuar = false
            }
            else -> println("Opción no válida. Intente nuevamente.")
        }
    }
}