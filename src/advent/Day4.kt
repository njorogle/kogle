package advent


private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(4) else listOf(
		"aczupnetwp-dnlgpyrpc-sfye-dstaatyr-561[patyc]",
		"jsehsyafy-vqw-ljsafafy-866[nymla]",
		"tyepcyletzylw-ncjzrpytn-prr-opawzjxpye-743[cnrdl]"
	)
}

object Factory {
	val regex = """([\w-]+)-(\d+)\[(\w{5})\]""".toRegex()

	fun code(encoded: String): Code {
		val match: MatchResult = regex.matchEntire(encoded)!!
		fun MatchResult.g(group: Int) = this.groupValues[group]
		return Code(match.g(1), match.g(2), match.g(3))
	}
}

data class Code(val code: String, val sector: String, val checksum: String)

fun main(args: Array<String>) {
	println(Factory.code("aaaaa-bbb-z-y-x-123[abxyz]"))

}
