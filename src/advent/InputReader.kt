package advent

import org.apache.http.client.fluent.Request

fun readInput(day: Int): List<String> =
	Request.Get("http://adventofcode.com/2016/day/$day/input")
		.addHeader("Cookie", "session=53616c7465645f5f2d9f24b4233cba8c1bd5db1685982422be481a7f0abffb8a62cef77cd2be4804b588c41aac0eecfa")
		.execute().returnContent().asString().trim().split("\n")

