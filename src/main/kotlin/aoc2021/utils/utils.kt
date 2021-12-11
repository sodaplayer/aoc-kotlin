package aoc2021.utils

import java.io.InputStream

fun loadInput(path: String): InputStream {
    return checkNotNull({}::class.java.getResourceAsStream(path))
}

fun <T> Iterable<T>.head(): T = this.first()
fun <T> Iterable<T>.tail(): Iterable<T> = this.drop(1)

fun <T> Iterable<T>.car(): T = this.first()
fun <T> Iterable<T>.cdr(): Iterable<T> = this.drop(1)
