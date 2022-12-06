package aoc2022

import utils.alsoPrintln
import utils.loadInput
import kotlin.streams.asSequence

fun main() {
    val lines = loadInput("/2022/day5")
        .bufferedReader()
        .lines()
        .asSequence()
//        .partition {
//        }

    // Part 1

    // Part 2

}

//   1   5   9  13  17  21  25  29  33
// [Q] [Q] [B] [D] [J] [W] [H] [R] [J]

//fun parseStackLine(line: String): Map<Int, String> {

//    val charArray = line.toCharArray()

//    charArray
//        .flatMapIndexed { index, c -> index to c }
//
//}

// make stacks
