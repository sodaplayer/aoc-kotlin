package aoc2022

import utils.alsoPrintln
import utils.loadInput

fun main() {
    val lines = loadInput("/2022/day4")
        .bufferedReader()
        .readLines()

    // Part 1
    lines.map { line ->
        val (first, second) = line.split(',')

        val (firstStart, firstEnd) = first.split('-')
        val (secStart, secEnd) = second.split('-')

        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secStart.toInt()..secEnd.toInt()

        if ((firstRange.contains(secondRange.first) && firstRange.contains(secondRange.last)) ||
            (secondRange.contains(firstRange.first) && secondRange.contains(firstRange.last))
        ) 1 else 0
    }.sum().alsoPrintln()

    // Part 2
    lines.map { line ->
        val (first, second) = line.split(',')

        val (firstStart, firstEnd) = first.split('-')
        val (secStart, secEnd) = second.split('-')

        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secStart.toInt()..secEnd.toInt()

        if (firstRange.contains(secondRange.first)
            || firstRange.contains(secondRange.last)
            || secondRange.contains(firstRange.first)
            || secondRange.contains(firstRange.last)
        ) 1 else 0
    }.sum().alsoPrintln()

}
