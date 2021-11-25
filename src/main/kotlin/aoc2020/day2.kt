package aoc2020.day1

import aoc2020.utils.loadInput

fun main() {
    val numbers = loadInput("/2020/day1")
        .bufferedReader()
        .readLines()
        .map(String::toInt)

    val complements = numbers.associateBy { 2020 - it }

    val allPairs = sequence {
        for ((i, m) in numbers.withIndex()) {
            for (n in numbers.subList(i, numbers.size)) {
                if (n + m > 2020) {
                    continue
                }
                yield(Triple(m, n, m+n))
            }
        }
    }

    val asdf = allPairs.firstNotNullOf { (a, b, sum) ->
        complements[sum]?.let {
            Triple(a, b, it)
        }
    }

    asdf.let { (a, b, c) -> {
            println(a * b * c)
        }
    }

    val forTwo = numbers.findPairOfSum(2020)
    println(forTwo)
    println(forTwo.first * forTwo.second)
}

private fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int> {
    val complements = associateBy { sum - it }

    return firstNotNullOf { number ->
        complements[number]?.let { Pair(it, number) }
    }
}

