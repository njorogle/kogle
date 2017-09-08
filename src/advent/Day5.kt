package advent

import java.security.MessageDigest

fun md5(instance: MessageDigest, input: String): String {
	val digest = instance.digest(input.map { it.toByte() }.toByteArray())
	var hash = ""
	for (i in 0 until digest.size) {
		val v = 0xff and digest[i].toInt()
		if (v <= 0xf) hash += "0"
		hash += Integer.toHexString(v)
//		hash += String.format("%02x", v)   // slower
	}
	return hash
}

private fun password1(start: String): String {
	val digest = MessageDigest.getInstance("MD5")
	var i = 0
	val zeros = "00000"
	var password = ""
	while (password.length < 8) {
		val md5 = md5(digest, start + i)
		if (md5.startsWith(zeros)) {
			password += md5[5]
		}
		i++
	}
	return password
}

private fun password2(start: String): String {
	val digest = MessageDigest.getInstance("MD5")
	var i = 0
	val zeros = "00000"
	val password = CharArray(8)
	var remaining = 8
	while (remaining > 0) {
		val md5 = md5(digest, start + i)
		if (md5.startsWith(zeros)) {
			val hexdex = md5[5];
			val index = when {
				hexdex < 'a' -> hexdex - '0'
				else -> hexdex - 'a' + 10
			}
			if (index < 8 && password[index] == 0.toChar()) {
				password[index] = md5[6]
				println(password.joinToString(""))
				remaining--
			}
		}
		i++
	}
	return password.joinToString("")
}

fun main (args: Array<String>) {
	var start = "cxdnnyjw"
//	start = "abc"

//	println(password1(start))
	println(password2(start))

}
