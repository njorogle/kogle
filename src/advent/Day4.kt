package advent


private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(4) else listOf(
		"aaaaa-bbb-z-y-x-123[abxyz]",
		"a-b-c-d-e-f-g-h-987[abcde]",
		"not-a-real-room-404[oarel]",
		"totally-real-room-200[decoy]"
	)
}

data class Code(val encoded: String, val code: String, val sector: Int, val checksum: String) {
	companion object {
		fun of(encoded: String): Code {
			val regex = """([\w-]+)-(\d+)\[(\w{5})]""".toRegex()
			val match: MatchResult = regex.matchEntire(encoded)!!
			fun MatchResult.g(group: Int) = this.groupValues[group]
			return Code(encoded, match.g(1), match.g(2).toInt(), match.g(3))
		}
	}

	val decoded: String get() {
		fun Char.rotate(n: Int): Char {
			val c = this + (n % 26)
			val overflow = c - 'z'
			return if (overflow > 0) 'a' + overflow - 1 else c
		}
		return code.map {
			when (it) {
				'-' -> ' '
				else -> it.rotate(sector)
			}
		}.joinToString(separator = "", postfix = "  ($sector)")
	}

	fun isValid(): Boolean {
		// Interesting map syntax:
//		val counts = code.fold(HashMap<Char, Int>()) { m, c ->
//			if (c != '-') {
//				m[c] = (m[c] ?: 0) + 1
//			}
//			m
//		}
		val correctsum = code
			.filter { it != '-' }
			.groupBy { it }
			.map { (k, v) -> Pair(k, v.size) }
			.sortedWith(compareBy({ -it.second }, { it.first }))
			.take(5)
			.map { it.first }
			.joinToString(separator = "")
		return correctsum == checksum
	}
}

private fun sectorSum(input: List<String>): Int {
	return input
		.map { Code.of(it) }
		.filter { it.isValid() }
		.sumBy { it.sector }
}

fun main(args: Array<String>) {
	val input = input(prod = false)

	println(sectorSum(input))

	input
		.map { Code.of(it) }
		.filter { it.isValid() }
		.map { it.decoded }
		.forEach { println(it) }
}
