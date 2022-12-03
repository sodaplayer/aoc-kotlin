package aoc2022

import utils.loadInput
import utils.partitionWhen

fun main() {
    val cals = loadInput("/2022/day1")
        .bufferedReader()
        .readLines()
        .partitionWhen {
            _, s -> s.isEmpty()
        }
        .map { it.map(String::toInt).sum() }


    // Part One
    cals
        .max()
        .also { println(it) }

    cals.sortedDescending()
        .take(3)
        .sum()
        .also { print(it) }
}

