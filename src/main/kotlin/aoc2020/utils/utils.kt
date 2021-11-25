package aoc2020.utils

import java.io.InputStream

fun loadInput(path: String): InputStream {
    return checkNotNull({}::class.java.getResourceAsStream(path))
}
