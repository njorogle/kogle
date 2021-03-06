package advent

// TODO how do I create a cache of the keys?  object class?

interface Key {
	val button: String
	fun press(input: String): Key
}

class SimpleKey(val x: Int, val y: Int): Key {

	override val button: String get() = (y * 3 + x + 1).toString()

	override fun press(input: String): Key {
		return input.fold(this, { k, c ->
			when (c) {
				'U' -> k.moveY(-1)
				'D' -> k.moveY(1)
				'L' -> k.moveX(-1)
				'R' -> k.moveX(1)
				else -> throw IllegalArgumentException("Derp: $c")
			}
		})
	}

	private fun moveX(step: Int) = SimpleKey(clamp(x + step), y)
	private fun moveY(step: Int) = SimpleKey(x, clamp(y + step))
	private fun clamp(v: Int) = Math.min(2, Math.max(v, 0))
}

class AdvancedKey(val x: Int, val y: Int): Key {

//	    1
//	  2 3 4
//	5 6 7 8 9
//	  A B C
//	    D

	override val button: String get() {
		return when ("$x,$y") {
			"2,0" -> '1'
			"1,1" -> '2'
			"2,1" -> '3'
			"3,1" -> '4'
			"0,2" -> '5'
			"1,2" -> '6'
			"2,2" -> '7'
			"3,2" -> '8'
			"4,2" -> '9'
			"1,3" -> 'A'
			"2,3" -> 'B'
			"3,3" -> 'C'
			"2,4" -> 'D'
			else -> throw IllegalStateException("Derp $x, $y")
		}.toString()
	}

	override fun press(input: String): Key {
		return input.fold(this, { k, c ->
			when (c) {
				'U' -> k.moveY(-1)
				'D' -> k.moveY(1)
				'L' -> k.moveX(-1)
				'R' -> k.moveX(1)
				else -> throw IllegalArgumentException("Derp: $c")
			}
		})
	}

	private fun moveX(step: Int): AdvancedKey {
		val (min, max) = trim(y)
		return AdvancedKey(clamp(x + step, min, max), y)
	}

	private fun moveY(step: Int): AdvancedKey {
		val (min, max) = trim(x)
		return AdvancedKey(x, clamp(y + step, min, max))

	}

	private fun trim(v: Int): Pair<Int, Int> {
		val trim = Math.abs(v - 2)
		return Pair(trim, 4 - trim)
	}

	private fun clamp(v: Int, min: Int, max: Int) = Math.min(max, Math.max(v, min))

}

fun enterCode(start: Key, input: String): String {
    var key = start
	var code = ""
	for (s in input.split("\\s".toRegex())) {
		key = key.press(s)
		code += key.button
	}
	return code
}

fun main(args: Array<String>) {
    var code0 = """
		|ULL
		|RRDDD
		|LURDL
		|UUUUD""".trimMargin()

	var code1 = """
		|DLUUULUDLRDDLLLUDULLULLRUURURLUULDUUUDLDDRUDLUULLRLDDURURDDRDRDLDURRURDLDUURULDDULDRDDLDLDLRDRUURLDLUDDDURULRLLLLRLULLUDRDLDUURDURULULULRLULLLULURLRDRDDDDDDDLRLULUULLULURLLDLRLUDULLDLLURUDDLDULDLULDDRLRLRDDLRURLLLURRLDURRDLLUUUUDRURUULRLDRRULLRUDLDRLUDRDRDRRDDURURRDRDRUDURDLUDRUDLRRULDLRDDRURDDUUDLDRDULDDRRURLLULRDRURLRLDLLLUULUUDLUDLDRRRRDUURULDUDUDRLDLLULLLRDDDDDLRDDLLUULLRRRDURLRURDURURLUDRRLRURDRDRRRRULUDLDRDULULRUDULLLUDRRLRLURDDURULDUUDULLURUULRDRDULRUUUDURURDDRRUDURRLRDRULRUUU
		|LDRURRUUUULDRDDDLLULDRUDDRLLDLDRDLRUDDDLDDULULULLRULDUDRRDLRUURURDRURURDLLRUURDUUDRLDURDRDLRRURURDUUUURUURRLLLDRDUURRRRURULUUUDLUDDRUURRLDULRDULRRRRUDURRLURULRURRDRDLLDRRDUDRDURLDDRURULDRURUDDURDLLLUURRLDRULLURDRDRLDRRURRLRRRDDDDLUDLUDLLDURDURRDUDDLUDLRULRRRDRDDLUDRDURDRDDUURDULRRULDLDLLUDRDDUDUULUDURDRLDURLRRDLDDLURUDRLDUURLLRLUDLLRLDDUDLLLRRRLDLUULLUDRUUDRLDUUUDUURLRDDDDRRDRLDDRDLUDRULDDDRDUULLUUUUULDULRLLLRLLDULRDUDDRDDLRRLRDDULLDURRRURDDUDUDDRLURRLUUUULLDRDULUUDRDULDLLUDLURDLLURRDLUULURRULRLURRRRRUURDDURLRLLDDLRRDUUURDRDUDRDDDLLDDRDRRRLURRDUULULULULRRURDDLDDLLLRUDDDDDDLLLRDULURULLRLRDRR
		|DDRLLLDLRRURRDLDDRUURRURRLRRRRUURUURDLURRRDDLRUDRURLUURLLRRLRLURLURURDULLLLDLRURULUUDURRLULRDRDRRDDLLULRLUDLUUUDRLLRRURRLDULDDLRRLUUUUDDLRLDRLRRDRDLDDURDDRDDLDLURLRRRDDUDLLRLRLURRRRULLULLLLDRLDULDLLDULRLDRDLDDRRDDDDRUDRLLURULRLDDLLRRURURDDRLLLULLULDDRDLDDDLRLLDRLDRUURRULURDDRLULLDUURRULURUUDULLRUDDRRLLDLLRDRUDDDDLLLDDDLLUUUULLDUUURULRUUDUUUDDLDURLDRDRRLLUDULDLUDRLLLDRRRULUUDDURUDRLUDDRRLLDUDUURDDRURLUURDURURURRUUDUDDLLLDRRRURURRURDLRULLDUDRLRLLRUDRUDLR
		|RRRDRLRURLRRLUURDRLDUURURLRDRRUDLLUUDURULLUURDLLDRRLURRUDUUDRRURLRRDULLDDLRRRUDUUDUUDLDDDLUUDLDULDDULLDUUUUDDUUDUDULLDDURRDLRRUDUDLRDUULDULRURRRLDLLURUDLDDDRRLRDURDLRRLLLRUDLUDRLLLRLLRRURUDLUDURLDRLRUDLRUULDRULLRLDRDRRLDDDURRRUDDDUDRRDRLDDRDRLLRLLRDLRDUDURURRLLULRDRLRDDRUULRDDRLULDLULURDLRUDRRDDDLDULULRDDRUDRLRDDRLDRDDRRRDUURDRLLDDUULRLLLULLDRDUDRRLUUURLDULUUURULLRLUDLDDLRRDLLRDDLRDRUUDURDDLLLDUUULUUDLULDUDULDRLRUDDURLDDRRRDLURRLLRRRUDDLDDRURDUULRUURDRRURURRRUUDUDULUDLUDLLLUUUULRLLRRRRDUDRRDRUDURLUDDLDRDLDDRULLRRULDURUL
		|DLLLRDDURDULRRLULURRDULDLUDLURDDURRLLRRLLULRDLDRDULRLLRDRUUULURRRLLRLDDDRDRRULDRRLLLLDLUULRRRURDDRULLULDDDLULRLRRRUDRURULUDDRULDUDRLDRRLURULRUULLLRUURDURLLULUURUULUUDLUDLRRULLLRRLRURDRRURDRULRURRUDUDDDRDDULDLURUDRDURLDLDLUDURLLRUULLURLDDDURDULRLUUUDLLRRLLUURRDUUDUUDUURURDRRRRRRRRRUDULDLULURUDUURDDULDUDDRDDRDRLRUUUUDLDLRDUURRLRUUDDDDURLRRULURDUUDLUUDUUURUUDRURDRDDDDULRLLRURLRLRDDLRUULLULULRRURURDDUULRDRRDRDLRDRRLDUDDULLDRUDDRRRD
		""".trimMargin()

    println(enterCode(SimpleKey(1, 1), code0))
    println(enterCode(SimpleKey(1, 1), code1))
    println(enterCode(AdvancedKey(0, 2), code0))
    println(enterCode(AdvancedKey(0, 2), code1))
}
