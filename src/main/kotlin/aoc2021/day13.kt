package aoc2021

import aoc2021.utils.loadInput
import aoc2021.utils.partitionWhen

fun main() {
    val pattern = Regex("""(\w+)-(\w+)""")
    val pattern2 = Regex("""(\w+)-(\w+)""")


    val edges = loadInput("/2021/day13")
        .bufferedReader()
        .readLines()
        .partitionWhen { _, s -> s.isBlank() }

    println(edges)

}
