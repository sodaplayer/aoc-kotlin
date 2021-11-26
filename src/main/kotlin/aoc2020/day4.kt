package aoc2020.day4

import aoc2020.utils.loadInput

fun main() {
    val passports = loadInput("/2020/day4")
        .bufferedReader()
        .readText()
        .trim()
        .split("\n\n")
        .onEach(::println)
        .count()


}
