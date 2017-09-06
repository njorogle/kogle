package koans

import java.util.*

/*
 * Your task is to implement the sum() function so that it computes the sum of
 * all elements in the given array a.
 */
fun sum(a: IntArray): Int =
        a.fold(0) { x, y -> x + y }
//        a.fold(0, { x, y -> x + y })
//        a.reduce(0) { x, y -> x + y }  // TODO ?
//        a.reduce { x, y -> x + y }
//        a.sum()
//        a.sumBy { it }
//        a.fold(0, { (sum, v) -> return sum + v })

fun indexOfMax(a: IntArray): Int? {
    if (a.isEmpty()) {
        return null
    }
    var maxdex = 0
    var max: Int = a[0]

    for (i in 1 until a.size) {
        if (a[i] > max) {
            max = a[i]
            maxdex = i
        }
    }
//    return maxdex
    max = Int.MIN_VALUE
    val foldIndexed = a.foldIndexed(0, fun(index: Int, maxdex: Int, v: Int): Int {
        if (v > max) {
            max = v
            return index
        }
        return maxdex
    })
    return foldIndexed
}

fun runs(a: IntArray): Int {
    var runs = 0;
    var cur: Int? = null
    for (v in a) {
        if (v != cur) {
            runs++;
            cur = v;
        }
    }
    return runs
}

fun isPalindrome(s: String): Boolean {
    for (i in 0 until s.length / 2) {
        if (s[i] != s[s.length - i - 1]) {
            return false
        }
    }
    return true
}

fun findPairless(a: IntArray): Int = a.fold(0, { x, y -> x xor y })


fun <T, C: MutableCollection<T>> Collection<T>.partitionTo(a: C, b: C, predicate: (T) -> Boolean): Pair<C, C> {
    for (e in this) {
        if (predicate(e)) {
            a.add(e)
        } else {
            b.add(e)
        }
    }
    return Pair(a, b)
}

fun partitionWordsAndLines() {
    val (words, lines) = listOf("a", "a b", "c", "d e").
            partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
    words == listOf("a", "c")
    lines == listOf("a b", "d e")
}


fun main(args: Array<String>) {
//    println(indexOfMax(intArrayOf()))
    println(indexOfMax(intArrayOf(1, 2, 3)))
}
