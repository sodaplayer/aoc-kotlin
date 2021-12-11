package aoc2021

import aoc2021.utils.loadInput
import java.util.*

fun main() {
    // part 1
    val lines = loadInput("/2021/day3")
        .bufferedReader()
        .readLines()

    fun add(count: List<Int>, line: String) =
        count.zip(line.toCharArray().map { it.digitToInt() })
            .map { it.first + it.second }

    val counts =  lines
        .fold(Collections.nCopies(12, 0), ::add)

    val gamma = counts
        .map { if (it > lines.size / 2) '1' else '0' }
        .joinToString(separator = "")
        .toBigInteger(2)

    val epsilon = counts
        .map { if (it > lines.size / 2) '0' else '1' }
        .joinToString(separator = "")
        .toBigInteger(2)

    println("gamma: $gamma")
    println("epsilon: $epsilon")
    println("power: ${gamma * epsilon}")

    // part 2
    fun oxygenRating(nums: List<String>) =
        if (nums.partition { it.first() == '1' }
                .run { first.size >= second.size })
            '1'
        else
            '0'

    fun carbonRating(nums: List<String>) =
        if (nums.partition { it.first() == '0' }
                .run { first.size <= second.size })
            '0'
        else
            '1'

    fun ratingSeq(ratingMethod: (List<String>) -> Char)  =
    generateSequence(lines.filterForRating(ratingMethod)) { (_, pass, _) ->
        if (pass.size > 1) pass.filterForRating(ratingMethod)
        else pass.firstOrNull()
            ?.let { lastNum ->
                lastNum.firstOrNull()?.let {
                    FilterResult(it, listOf(lastNum.substring(1)), emptyList())
                }
            }
    }

    fun rating(ratingMethod: (List<String>) -> Char) =
        ratingSeq(ratingMethod)
            .map { it.bit }
            .joinToString("")
            .toInt(2)

    val oxygen = rating(::oxygenRating)
    val carbon = rating(::carbonRating)

    println("oxygen: $oxygen")
    println("carbon: $carbon")
    println("life support: ${oxygen * carbon}")

}

data class FilterResult(val bit: Char, val pass: List<String>, val fail: List<String>)
fun FilterResult.get(i: Char): List<String> {
    return if (i == bit) pass else fail
}

fun List<String>.filterForRating(ratingMethod: (List<String>) -> Char): FilterResult {
    val bit = ratingMethod(this)
    val (pass, fail) = this.partition { it.first() == bit }
        .run {
            Pair(
                first.map { it.substring(1) },
                second.map { it.substring(1) }
            )
        }

    return FilterResult(
        bit,
        pass,
        fail
    )
}


