package advent


private fun input(prod: Boolean = false): List<String> {
	return if (prod) readInput(4) else listOf(
		"aczupnetwp-dnlgpyrpc-sfye-dstaatyr-561[patyc]",
		"jsehsyafy-vqw-ljsafafy-866[nymla]",
		"tyepcyletzylw-ncjzrpytn-prr-opawzjxpye-743[cnrdl]"
	)
}

data class Code(val encoded: String) {
	private val regex = """([\w-]+)-(\d+)\[(\w{5})\]""".toRegex()

	val code: String get() = extract(1)
	val sector: String get() = extract(2)
	val checksum: String get() = extract(3)

	private fun extract(group: Int) = regex.matchEntire(encoded)!!.groupValues[group]
}

fun main(args: Array<String>) {
	println(Code("aaaaa-bbb-z-y-x-123[abxyz]"))

}
