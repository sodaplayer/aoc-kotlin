package aoc2022

import utils.alsoPrintln
import utils.loadInput
import kotlin.streams.toList

fun main() {
    val line = loadInput("/2022/day6")
        .bufferedReader()
        .readLine()

    // Part 1
    line.windowed(4)
        .mapIndexed { i, s ->
            i + 4 to s
        }
        .first { (_, s) -> s.toSet().size == 4 }
        .alsoPrintln()

    // Part 2
    line.windowed(14)
        .mapIndexed { i, s ->
            i + 14 to s
        }
        .first { (_, s) -> s.toSet().size == 14 }
        .alsoPrintln()
}
