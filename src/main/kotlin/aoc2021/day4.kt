package aoc2021

import aoc2021.utils.loadInput

data class Board(val marked: Set<Int>, val posMap: Map<String, Int>)
data class Round(val justCalled: String?, val nums: List<String>, val boards: List<Board>)

fun main() {
    val lines = loadInput("/2021/day4")
        .bufferedReader()
        .readLines()

    val nums = lines[0].split(",")

    val boards = lines.drop(1)
        .chunked(6)
        .map{
            it.joinToString( " ").split("\n", " ").filter(String::isNotBlank)
        }
        .map(::initBoard)

    val playedRounds = generateSequence (Round(null as String?, nums, boards)) { (_, rounds, boards) ->
        Round(
            rounds.first(),
            rounds.drop(1),
            boards.map { markNum(it, rounds.first()) }
        )
    }

    val firstBingo = playedRounds.filter { (_, _, boards) ->
        boards.any(::bingo)
    }.first()

    val firstBoard = firstBingo.boards.find(::bingo)

    println(sunUnmarked(firstBoard!!) * firstBingo.justCalled!!.toInt())

    val (prev, final) = playedRounds.take(100)
        .partition {
                (_, _, boards) -> boards.count(::bingo) < 100
        }
        .let { it.first.last() to it.second.first() }

    val worstBoard =
        final.boards.toSet().filterNot { finalBoard ->
            prev.boards
                .filter(::bingo)
                .map { it.posMap.keys }
                .contains(finalBoard.posMap.keys)
        }.first()


    println(sunUnmarked(worstBoard) * final.justCalled!!.toInt())
}

fun initBoard(input: List<String>): Board {
    return Board(
        emptySet(),
        input.mapIndexed { pos, num ->
            num to pos
        }.toMap()
    )
}



fun markNum(board: Board, number: String): Board {
    val pos = board.posMap[number]

    return if (pos == null) board
    else with(board) {
        copy(
            marked = marked + pos
        )
    }
}

val winningBoards = listOf(
    /* Columns */
    0 until 5,
    5 until 10,
    10 until 15,
    15 until 20,
    20 until 25,

    /* Rows */
    0 until 21 step 5,
    1 until 22 step 5,
    2 until 23 step 5,
    3 until 24 step 5,
    4 until 24 step 5,
)

fun bingo(board: Board) =
    winningBoards.map { positions ->
        positions.count {
            board.marked.contains(it)
        }
    }.any { it == 5 }

fun sunUnmarked(board: Board) =
    board.posMap.filterNot { (_, pos) ->
        board.marked.contains(pos)
    }.keys.sumOf(String::toInt)
