package aoc2021

import aoc2021.utils.head
import aoc2021.utils.loadInput
import aoc2021.utils.tail

fun main() {
    val grid = loadInput("/2021/day11")
        .bufferedReader()
        .readLines()
        .flatMap {
            it.asSequence()
        }
        .mapIndexed { i, c -> i to (c.code - 48) }
        .toMap()

    val sim = generateSequence(Step(grid, 0)) { (stepState, total) ->
            val initial = initializeStep(stepState)

            val seq = generateSequence(initial) { (state, queue) ->
                if (queue.none()) null
                else applyFlash(state, queue.head())
                    .let {
                        it.copy(queue = it.queue + queue.tail())
                    }
            }


            finalizeStep(seq.last().state)
                .let {
                    it.copy(total = it.total + total)
                }
        }

    val part1 = sim.take(1 + 100).last()
    drawState(part1.state)
    println(part1.total)

    val part2 = sim
        .takeWhile { (state, _) ->
            state.values.any { it != 0 }
        }
        .count()
    println(part2)
}

typealias State = Map<Int, Int?>

data class Step(val state: State, val total: Int)
data class FlashResult(val state: State, val queue: Iterable<Int>)

fun applyFlash(state: State, index: Int): FlashResult =
    if (state[index] == null) FlashResult(state, emptyList())
    else {
        val neighbors = neighbors(index)

        val updates = neighbors.map {
            it to state[it]?.inc()
        }

        val updated = state + updates + (index to null)

        FlashResult(
            updated,
            updated.filterValues { (it ?: 0) > 9 }.keys.sorted()
        )
    }

fun initializeStep(state: State): FlashResult {
    val updated = state.mapValues { (_, i) ->
        i!!.inc()
    }

    return FlashResult(
        updated,
        updated.filterValues { it > 9 }.keys.sorted()
    )
}

/** Tally flashed octopuses and reset energy levels to 0 */
fun finalizeStep(state: State): Step {
    val flashed = state
        .filterValues { it == null }
        .mapValues { 0 }

    return Step(
        state + flashed,
        flashed.size
    )
}



/**
 * @param i index into a 10x10 grid starting NW, going horizontal to NE, then row-by-row ending at SE
 */
fun neighbors(i: Int): List<Int> {
    val x = i % 10
    val y = i / 10

    /*      | -1    +0     +1
     *   -1 | -1,-1 +0,-1, +1,-1
     *   +0 | -1,+0 +0,+0, +1,+0
     *   +1 | -1,+1 +0,+1, +1,+1
     */

    val nw = if (x > 0 && y > 0) x - 1 + (y - 1) * 10 else null
    val w  = if (x > 0         ) x - 1 + (y + 0) * 10 else null
    val sw = if (x > 0 && y < 9) x - 1 + (y + 1) * 10 else null

    val n  = if (         y > 0) x + 0 + (y - 1) * 10 else null
    //  c  = if (              ) x + 0 + (y + 0) * 10
    val s  = if (         y < 9) x + 0 + (y + 1) * 10 else null

    val ne = if (x < 9 && y > 0) x + 1 + (y - 1) * 10 else null
    val e  = if (x < 9         ) x + 1 + (y + 0) * 10 else null
    val se = if (x < 9 && y < 9) x + 1 + (y + 1) * 10 else null

    return listOfNotNull(nw, n, ne, w, e, sw, s, se)
}

/*
 *  0  1  2  3  4  5  6  7  8  9
 * 10 11 12 13 14 15 16 17 18 19
 * 20 21 22 23 24 25 26 27 28 29
 * 30 31 32 33 34 35 36 37 38 39
 * 40 41 42 43 44 45 46 47 48 49
 * 50 51 52 53 54 55 56 57 58 59
 * 60 61 62 63 64 65 66 67 68 69
 * 70 71 72 73 74 75 76 77 78 79
 * 80 81 82 83 84 85 86 87 88 89
 * 90 91 92 93 94 95 96 97 98 99
 */
fun drawGridIndices() {
    drawState((0..99).associateWith { it })
}

fun drawState(state: State, withIndex: Boolean = false) {
    (0..9).forEach { y ->
        (0..9).forEach { x ->
            if (withIndex) print("%2d:%2d| ".format(x + y * 10, state[x + y * 10]?: 0))
            else print("%2d ".format(state[x + y * 10]?: 0))
        }
        println()
    }
}

