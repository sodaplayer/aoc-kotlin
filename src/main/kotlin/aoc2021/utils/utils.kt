package aoc2021.utils

import java.io.InputStream

fun loadInput(path: String): InputStream {
    return checkNotNull({}::class.java.getResourceAsStream(path))
}

fun <T> List<T>.tail(): List<T> = this.drop(1)

fun <T> List<T>.head(): T = this.first()
