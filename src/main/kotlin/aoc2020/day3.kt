package aoc2020.day3

import aoc2020.utils.loadInput

fun main() {
    val trees = loadInput("/2020/day3")
        .bufferedReader()
        .readLines()

    listOf(
        traverse(trees, 1),
        traverse(trees, 3),
        traverse(trees, 5),
        traverse(trees, 7),
        traverse(trees
            .filterIndexed { i, _ -> i % 2 == 0 },
            1),
    ).reduce { a, b -> a * b }
        .apply(::println)
}

private fun traverse(trees: List<String>, stepSize: Int) =
    trees.filterIndexed { index, line ->
        line[(index * stepSize) % 31] == '#'
    }.count()
        .apply(::println)
