package aoc2020.day4

import aoc2020.utils.loadInput

fun main() {
    val passports = loadInput("/2020/day4")
        .bufferedReader()
        .readText()
        .trim()
        .splitPassports()
        .map { passport -> passport
            .splitFields()
            .associate { it.toAttributes() } }
        .onEach(::println)
}

fun String.toAttributes(): Pair<String, String> {
    val (key, value) = this.split(":")
    return key to value
}

fun String.splitPassports(): List<String> {
    return this.split("\n\n")
}

fun String.splitFields(): List<String> {
    return this.split(" ", "\n")
}
