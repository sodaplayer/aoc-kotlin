package aoc2021

import aoc2021.utils.loadInput
import aoc2021.utils.partitionWhen
import kotlin.math.max
import kotlin.math.min

fun main() {
    val pattern = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")

    val lines = loadInput("/2021/day5-test")
        .bufferedReader()
        .readLines()
        .map {
            val (x1, y1, x2, y2) = pattern.find(it)!!.destructured
            Line(
                x1.toInt(),
                y1.toInt(),
                x2.toInt(),
                y2.toInt()
            )
        }

    val orthogonal = lines.filter { (x1, y1, x2, y2) ->
        x1 == x2 || y1 == y2
    }

    val (horizontal, vertical) = orthogonal.partition {
        it.y1 == it.y2
    }

    val horRanges =
        horizontal.groupBy({it.x1}) {
            min(it.x1, it.x2) .. max(it.x1, it.x2)
        }
        .mapValues { (_, ranges) ->
            ranges.sortedWith(compareBy({ it.first }, { it.last }))
        }

    // val horOverlaps = horRanges.filterValues { it.size > 1 }

    println(
        listOf(0..2, 0..3, 3..4, 5..6, 6..7).groupOverlapping().toList()
    )
}


data class RangeMerge(val overlapping: Int, val ranges: List<IntRange>)
data class RangeMergeStep(val overlapping: Int, val ranges: List<IntRange>)
/** Merges contiguous ranges together and counts their overlaps */
//fun Sequence<IntRange>.mergeRanges(): RangeMerge {
//}

/** Separates a sorted sequence of ranges into groups of overlapping ones */
fun Sequence<IntRange>.groupOverlapping(): Sequence<List<IntRange>> {
    return partitionWhen { prev, next -> prev.last < next.first }
}

fun Iterable<IntRange>.groupOverlapping(): Sequence<List<IntRange>>
        = this.asSequence().groupOverlapping()

//data class

//fun countOverlaps

/* Assumes other begins after this and other's start is contained in this */
fun IntRange.overlap(other: IntRange): Int = other.first - this.first + 1

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
