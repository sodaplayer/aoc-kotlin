package aoc2022

import utils.loadInput

fun main() {
    val lines = loadInput("/2022/day3")
        .bufferedReader()
        .readLines()

    // Part 1
    val compartmentalized = lines.map {
        it.chunked(it.length / 2)
    }

    val items = compartmentalized.flatMap {
        val (first, second) = it
        first.asIterable().toSet().intersect(second.toSet())
    }

    items.sumOf { it.toPriority() }
        .also { println(it) }


    // Part 2
    val groups = lines
        .map { it.toSet() }
        .chunked(3)
        .map { bags ->
            bags.reduce { a, b ->
                a.intersect(b)
            }.first()
        }

    groups.sumOf(Char::toPriority)
        .also { println(it) }
}

fun Char.toPriority(): Int {
    return if (this.isLowerCase()) this - 'a' + 1 else this - 'A' + 27
}
