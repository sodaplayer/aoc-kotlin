package aoc2021

import aoc2021.utils.head
import aoc2021.utils.loadInput
import aoc2021.utils.tail

fun main() {
    val numbers = loadInput("/2021/day1")
        .bufferedReader()
        .readLines()
        .map(String::toInt)

    val part1 = countIncreasing(numbers)
    println(part1)

    val windowed = numbers
        .windowed(3)
        .map { it.sum() }

    val part2 = countIncreasing(windowed)
    println(part2)
}

private fun countIncreasing(windowed: List<Int>) =
    windowed.tail()
        .fold(Pair(windowed.head(), 0)) { (last, count), i ->
            Pair(i, if (i > last) count + 1 else count)
        }

