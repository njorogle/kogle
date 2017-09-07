package advent

import java.util.*

data class Location(val x: Int, val y: Int) {
    val distance: Int get() = Math.abs(x) + Math.abs(y)
}

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
    private var visited: HashSet<Location> = hashSetOf(Location(0, 0))
    private var firstDoubleVisit: Location? = null

    var facing: Card = Card.N

    init {
        directions.split(", ")
            .forEach(this::move)
    }

    fun move(d: String) {
        facing = facing.rotate(d.first())
        val blocks = d.substring(1).toInt()
        when (facing) {
            Card.N -> moveY(blocks, +1)
            Card.E -> moveX(blocks, +1)
            Card.S -> moveY(blocks, -1)
            Card.W -> moveX(blocks, -1)
        }
    }

    fun moveX(distance: Int, direction: Int) {
        for (i in 1..distance) {
            x += direction
            visit()
        }
    }

    fun moveY(distance: Int, direction: Int) {
        for (i in 1..distance) {
            y += direction
            visit()
        }
    }

    private fun visit() {
        if (!visited.add(Location(x, y)) && firstDoubleVisit == null) {
            firstDoubleVisit = Location(x, y)
        }
    }

    fun distance(): Pair<Int, Int> = Pair(Math.abs(x) + Math.abs(y), firstDoubleVisit!!.distance)
}

fun main(args: Array<String>) {
    var directions = "R3, R1, R4, L4, R3, R1, R1, L3, L5, L5, L3, R1, R4, L2, L1, R3, L3, R2, R1, R1, L5, L2, L1, R2, L4, R1, L2, L4, R2, R2, L2, L4, L3, R1, R4, R3, L1, R1, L5, R4, L2, R185, L2, R4, R49, L3, L4, R5, R1, R1, L1, L1, R2, L1, L4, R4, R5, R4, L3, L5, R1, R71, L1, R1, R186, L5, L2, R5, R4, R1, L5, L2, R3, R2, R5, R5, R4, R1, R4, R2, L1, R4, L1, L4, L5, L4, R4, R5, R1, L2, L4, L1, L5, L3, L5, R2, L5, R4, L4, R3, R3, R1, R4, L1, L2, R2, L1, R4, R2, R2, R5, R2, R5, L1, R1, L4, R5, R4, R2, R4, L5, R3, R2, R5, R3, L3, L5, L4, L3, L2, L2, R3, R2, L1, L1, L5, R1, L3, R3, R4, R5, L3, L5, R1, L3, L5, L5, L2, R1, L3, L1, L3, R4, L1, R3, L2, L2, R3, R3, R4, R4, R1, L4, R1, L5"
	var d2 = readInput(1)

//    println(Directionator("R2, L3").distance())
//    println(Directionator("L2, L2, L2, L2, L2").distance())
    println(Directionator("R8, R4, R4, R8").distance())
    println(Directionator(directions).distance())
}
