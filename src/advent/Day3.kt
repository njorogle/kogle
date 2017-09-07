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
		" 10 10 10 "
	)
}

fun main(args: Array<String>) {
	val count = input(prod = true)
		.filter(::isTriangle)
		.count()

	println(count)
}
