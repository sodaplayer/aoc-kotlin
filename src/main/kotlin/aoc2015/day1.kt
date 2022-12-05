package aoc2015

import utils.alsoPrintln
import utils.loadInput


fun main() {
    val input = loadInput("/2015/day1")
        .bufferedReader()
        .readLine()

    // Part 1
    input
        .map { if (it == '(') 1 else -1 }
        .sum()
        .alsoPrintln()

    // Part 2

}
