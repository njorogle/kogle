package advent


private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(4) else listOf(
		"aczupnetwp-dnlgpyrpc-sfye-dstaatyr-561[patyc]",
		"jsehsyafy-vqw-ljsafafy-866[nymla]",
		"tyepcyletzylw-ncjzrpytn-prr-opawzjxpye-743[cnrdl]"
	)
}

data class Code(val code: String, val sector: String, val checksum: String) {
	companion object {
		fun of(encoded: String): Code {
			val regex = """([\w-]+)-(\d+)\[(\w{5})\]""".toRegex()
			val match: MatchResult = regex.matchEntire(encoded)!!
			fun MatchResult.g(group: Int) = this.groupValues[group]
			return Code(match.g(1), match.g(2), match.g(3))
		}
	}
}

fun main(args: Array<String>) {
	input().forEach { println(Code.of(it)) }

}
