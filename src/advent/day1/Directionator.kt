package advent.day1

enum class Card {
    N, E, S, W;

    fun rotate(dir: Char): Card {
        val values = values()
        return when (dir) {
            'L' -> values[(ordinal + values.size - 1) % values.size]
            'R' -> values[(ordinal + 1) % values.size]
            else -> throw IllegalArgumentException("Derp: $dir")
        }
    }
}

class Directionator(directions: String) {
    private var x = 0
    private var y = 0
    var facing: Card = Card.N

    init {
        directions.split(", ")
            .forEach(this::move)
    }

    fun move(d: String) {
        facing = facing.rotate(d.first())
        val blocks = d.substring(1).toInt()
        when (facing) {
            Card.N -> y += blocks
            Card.E -> x += blocks
            Card.S -> y -= blocks
            Card.W -> x -= blocks
        }
    }

    fun distance(): Int = Math.abs(x) + Math.abs(y)
}

fun main(args: Array<String>) {
    var directions = "R3, R1, R4, L4, R3, R1, R1, L3, L5, L5, L3, R1, R4, L2, L1, R3, L3, R2, R1, R1, L5, L2, L1, R2, L4, R1, L2, L4, R2, R2, L2, L4, L3, R1, R4, R3, L1, R1, L5, R4, L2, R185, L2, R4, R49, L3, L4, R5, R1, R1, L1, L1, R2, L1, L4, R4, R5, R4, L3, L5, R1, R71, L1, R1, R186, L5, L2, R5, R4, R1, L5, L2, R3, R2, R5, R5, R4, R1, R4, R2, L1, R4, L1, L4, L5, L4, R4, R5, R1, L2, L4, L1, L5, L3, L5, R2, L5, R4, L4, R3, R3, R1, R4, L1, L2, R2, L1, R4, R2, R2, R5, R2, R5, L1, R1, L4, R5, R4, R2, R4, L5, R3, R2, R5, R3, L3, L5, L4, L3, L2, L2, R3, R2, L1, L1, L5, R1, L3, R3, R4, R5, L3, L5, R1, L3, L5, L5, L2, R1, L3, L1, L3, R4, L1, R3, L2, L2, R3, R3, R4, R4, R1, L4, R1, L5"
//    println(Directionator("R2, L3").distance())
//    println(Directionator("L2, L2, L2, L2, L2").distance())
//    println(Directionator("R5, L5, R5, R3").distance())
    println(Directionator(directions).distance())
}
