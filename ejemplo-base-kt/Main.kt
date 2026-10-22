//clear; kotlinc (Get-ChildItem -Filter *.kt).FullName -include-runtime -d Main.jar; if ($LASTEXITCODE -eq 0) { java -jar Main.jar }
fun main() {
    val juegos = mutableListOf(
        Juego(1, "Catan", 35000, 10),
        Juego(2, "Carcassonne", 25000, 8),
        Juego(3, "Dixit", 30000, 12),
        Juego(4, "Azul", 32000, 6),
        Juego(5, "Ticket to Ride", 45000, 7),
        Juego(6, "Pandemic", 38000, 5),
        Juego(7, "7 Wonders", 42000, 9),
        Juego(8, "Splendor", 28000, 15),
        Juego(9, "Codenames", 22000, 20),
        Juego(10, "Dobble", 15000, 25),
        Juego(11, "Exploding Kittens", 18000, 18),
        Juego(12, "Sushi Go!", 14000, 14),
        Juego(13, "King of Tokyo", 33000, 6),
        Juego(14, "Wingspan", 55000, 4),
        Juego(15, "Terraforming Mars", 65000, 3),
        Juego(16, "Cascadia", 39000, 8),
        Juego(17, "The Crew", 17000, 12),
        Juego(18, "Patchwork", 24000, 10),
        Juego(19, "Love Letter", 12000, 20),
        Juego(20, "Ark Nova", 75000, 2)
    )
    
    iniciar(juegos)
    activarMenu(juegos)
}