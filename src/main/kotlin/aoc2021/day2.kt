package aoc2021

import aoc2021.utils.loadInput

fun main() {
    val pattern = Regex("""(\w+) (\d+)""")

    val coms = loadInput("/2021/day2")
        .bufferedReader()
        .readLines()
        .map {
            val (dir, units) = pattern.find(it)!!.destructured
            Pair(dir, units.toInt())
        }
        .fold(Pair(0, 0)) { (horz, vert), (dir, units) ->
            when(dir) {
                "forward" -> Pair(horz + units, vert)
                "up" -> Pair(horz, vert - units)
                "down" -> Pair(horz, vert + units)
                else -> Pair(horz, vert)
            }
        }

    val part1 = coms
    println(part1.first * part1.second)

    val coms2 = loadInput("/2021/day2")
        .bufferedReader()
        .readLines()
        .map {
            val (dir, units) = pattern.find(it)!!.destructured
            Pair(dir, units.toInt())
        }
        .fold(Triple(0, 0, 0)) { (horz, vert, aim), (dir, units) ->
            when(dir) {
                "forward" -> Triple(horz + units, vert + units * aim, aim)
                "up" -> Triple(horz, vert, aim - units)
                "down" -> Triple(horz, vert, aim + units)
                else -> Triple(horz, vert, aim)
            }
        }


    val part2 = coms2
    println(part2.first * part2.second)
}

