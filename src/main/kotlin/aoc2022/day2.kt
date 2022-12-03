package aoc2022

import utils.loadInput
import utils.partitionWhen

// A Rock
// B Paper
// C Scissors

// X Rock
// Y Paper
// Z Scissors

// 0 Lost
// 3 Lost
// 6 Won
fun main() {
    val lines = loadInput("/2022/day2")
        .bufferedReader()
        .readLines()

    // Part One
    lines.sumOf { play(it) }
        .also { println(it) }

    // Part Two
    lines.sumOf { play2(it) }
        .also { println(it) }
}


fun play(round: String): Int {
    val (opp, me) = round.split(' ')

    val outcome = when (opp) {
        "A" -> when (me) {
            "X" -> 3
            "Y" -> 6
            "Z" -> 0
            else -> 0
        }
        "B" -> when (me) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> 0
        }
        "C" -> when (me) {
            "X" -> 6
            "Y" -> 0
            "Z" -> 3
            else -> 0
        }
        else -> 0
    }

    val shape =
        when (me) {
            "X" -> 1
            "Y" -> 2
            "Z" -> 3
            else -> 0
        }
    return shape + outcome
}

fun play2(round: String): Int {
    val (opp, me) = round.split(' ')

    val outcome = when (opp) {
        "A" -> when (me) {
            "X" -> 0 + 3
            "Y" -> 3 + 1
            "Z" -> 6 + 2
            else -> 0
        }
        "B" -> when (me) {
            "X" -> 0 + 1
            "Y" -> 3 + 2
            "Z" -> 6 + 3
            else -> 0
        }
        "C" -> when (me) {
            "X" -> 0 + 2
            "Y" -> 3 + 3
            "Z" -> 6 + 1
            else -> 0
        }
        else -> 0
    }

    return outcome
}
