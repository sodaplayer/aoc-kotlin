package aoc2020.day1

import aoc2020.utils.loadInput

fun main() {
    val numbers = loadInput("/2020/day1")
        .bufferedReader()
        .readLines()
        .map(String::toInt)

    val numbersSet = numbers.toSortedSet()

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


//    allPairs.forEach(::println)

    val asdf =
        allPairs.firstNotNullOf { (a, b, sum) ->
            if (complements.contains(sum))
                Triple(a, b, complements[sum])
            else null
        }

    println(asdf.toList().reduce(*))

    val forTwo = numbers.findPairOfSum(2020);
    println(forTwo)
    println(forTwo.first + forTwo.second);

}

private fun List<Int>.findPairOfSum(sum: Int): Pair<Int, Int> {
    val complements = associateBy { sum - it }

    return firstNotNullOf { number ->
        complements[number]?.let { Pair(it, number) }
    }
}

