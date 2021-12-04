package aoc2021

import aoc2021.utils.loadInput
import java.util.*

fun main() {
    val lines = loadInput("/2021/day3")
        .bufferedReader()
        .readLines()

    val counts =  lines
        .fold(Collections.nCopies(12, 0), ::add)

    val gamma = counts
        .map { if (it > 500) '1' else '0' }
        .joinToString(separator = "")
        .toBigInteger(2)

    val epsilon = counts
        .map { if (it > 500) '0' else '1' }
        .joinToString(separator = "")
        .toBigInteger(2)

    println(gamma)
    println(epsilon)
    println(gamma * epsilon)
}

fun add(count: List<Int>, line: String) =
    count.zip(line.toCharArray().map { it.digitToInt() })
        .map { it.first + it.second }
