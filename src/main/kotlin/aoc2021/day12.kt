package aoc2021

import aoc2021.utils.loadInput

fun main() {
    val pattern = Regex("""(\w+)-(\w+)""")

    val edges = loadInput("/2021/day12")
        .bufferedReader()
        .readLines()
        .map {
            val (u, v) = pattern.find(it)!!.destructured
            Edge(u, v).normalize()
        }
        .manualPrune()

    val dotfile = edges.toDOT()
    println(dotfile)
}

val pruneable = listOf("CT")

fun Iterable<Edge>.manualPrune() =
    this.filterNot {
        pruneable.contains(it.u) || pruneable.contains(it.v)
    }

data class Edge(val u: String, val v: String): Comparable<Edge> {
    companion object {
        val vertexComparator: VertexComparator = VertexComparator()
    }

    class VertexComparator: Comparator<String> {
        override fun compare(a: String?, b: String?): Int {
            return when {
                a == b -> 0
                a == "start" -> -1
                a == "end" -> 1
                b == "start" -> 1
                b == "end" -> -1
                else -> (a ?: "~").compareTo(b ?: "~")
            }
        }

    }

    override fun compareTo(other: Edge): Int =
        compareBy<Edge, String>(vertexComparator) { it.u }
            .thenComparing(compareBy<Edge, String>(vertexComparator) { it.v })
            .compare(this, other)

    fun normalize(): Edge =
        if (vertexComparator.compare(u, v) <= 0) this
        else Edge(v, u)
}



fun String.isBig() = this.first().isUpperCase()
fun String.isSmall() = this.first().isLowerCase()

fun Iterable<Edge>.toDOT(): String =
    listOf("graph caves {")
        .plus(this.sorted().map { (u, v) -> "$u -- $v" }.toList())
        .plus(this
            .flatMap { (u, v) -> listOf(u, v) }
            .asSequence()
            .distinct()
            .filter { it.isBig() }
            .map { "$it [style=filled]" }
            .sorted()
        )
        .plus("start [shape=rarrow]")
        .plus("end [shape=doublecircle]")
        .plus("}")
        .joinToString("\n")
