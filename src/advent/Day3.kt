package advent

fun isTriangle(s: String): Boolean {
	val a = s.trim().split("\\s+".toRegex()).map { it.toInt() }
	return isTriangle(a[0], a[1], a[2])
}

fun isTriangle(a: Int, b: Int, c: Int): Boolean {
	return a + b > c && a + c > b && b + c > a
}

private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(3) else listOf(
		" 5 10 25 ",
		" 10 10 10 ",
		" 12 10 10 "
	)
}

fun <T> transposeBy3(list: List<T>): List<T> {
	val out = ArrayList<T>()
	for (i in 0 until list.size / 3) {
		val col = i % 3
		val row = i / 3
		val index = row * 9 + col
		out.add(list[index])
		out.add(list[index + 3])
		out.add(list[index + 6])
	}
	return out
}

fun main(args: Array<String>) {
	val production = true
	val transpose = true

	var list = input(production)
		.map { it.trim() }
		.flatMap { it.split("\\s+".toRegex()) }
		.map { it.toInt() }

	if (transpose) {
		list = transposeBy3(list)
	}

	var count = 0
	for (i in 0 until list.size step 3) {
		if (isTriangle(list[i], list[i + 1], list[i + 2])) {
			count++
		}
	}
	println(count)

//	val count = input(prod = false)
//		.filter(::isTriangle)
//		.count()
//
//	println(count)
}
