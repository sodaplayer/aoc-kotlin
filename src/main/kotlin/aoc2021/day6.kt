package aoc2021

import aoc2021.utils.loadInput
import kotlin.math.roundToInt

fun main() {
    val fishes = loadInput("/2021/day6")
        .bufferedReader()
        .readLines()
        .flatMap {
            it.split(",")
        }
        .map(String::toInt)

    // Part 1 - Naive solution which works but is slow
    val part1 = fishes
        .chunked((300.0 / 8).roundToInt())
        .parallelStream()
        .map { chunk ->
            generateSequence(chunk) {
                it.flatMap {
                    if (it == 0) listOf(6, 8)
                    else listOf(it - 1)
                }

            }.take(81).last().count()
        }
        .reduce { a, b -> a + b }

    println(part1)

    val part2 = fishes
        .groupBy { it }
        .mapValues { (_, group) -> group.count().toLong() }
        .let {
            generateSequence(it, ::tick)
        }
        .take(257)
        .last()
        .map { it.value }
        .sum()

    print(part2)

}

fun tick(fish: Map<Int, Long>) =
    mapOf(
        8 to (fish[0] ?: 0L),
        7 to (fish[8] ?: 0L),
        6 to (fish[7] ?: 0L) + (fish[0] ?: 0L),
        5 to (fish[6] ?: 0L),
        4 to (fish[5] ?: 0L),
        3 to (fish[4] ?: 0L),
        2 to (fish[3] ?: 0L),
        1 to (fish[2] ?: 0L),
        0 to (fish[1] ?: 0L),
    )
