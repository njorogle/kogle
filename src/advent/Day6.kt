package advent

import java.util.concurrent.atomic.AtomicInteger

private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(6) else listOf(
		"eedadn",
		"drvtee",
		"eandsr",
		"raavrd",
		"atevrs",
		"tsrnev",
		"sdttsa",
		"rasrtv",
		"nssdts",
		"ntnada",
		"svetve",
		"tesnvt",
		"vntsnd",
		"vrdear",
		"dvrsen",
		"enarar"
	)
}

fun freq(s: String, mostCommon: Boolean = true): String {
	val counts = HashMap<Char, AtomicInteger>()
	s.forEach { counts.computeIfAbsent(it, { AtomicInteger() }).incrementAndGet() }
	return counts
		.map { Pair(it.key, it.value.get()) }
		.sortedBy { if (mostCommon) -it.second else it.second }
		.map { it.first }
		.first().toString()
}

fun main(args: Array<String>) {
	val input = input(prod = true)
	val count = input.first().length

	val transposed = Array(count, { StringBuilder() })

	input
		.flatMap { it.asIterable() }
		.forEachIndexed { i, v -> transposed[i % count].append(v) }

	println(transposed.joinToString("") { freq(it.toString()) })
	println(transposed.joinToString("") { freq(it.toString(), mostCommon = false) })
}
