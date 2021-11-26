package aoc2020.day2

import aoc2020.utils.loadInput

private val regex = Regex("""(\d+)-(\d+) ([a-z]): ([a-z]+)""")

fun main() {
    val passwords = loadInput("/2020/day2")
        .bufferedReader()
        .readLines()
        .map(::parse)

    passwords
        .count(PasswordEntry::validPassword)
        .apply(::println)

    passwords
        .count(PasswordEntry::validPassword2)
        .apply(::println)
}

fun parse(line: String) =
    regex.matchEntire(line)!!
        .destructured
        .let { (low, high, letter, password) ->
            PasswordEntry(low.toInt(), high.toInt(), letter.first(), password)
        }

data class PasswordEntry(
    // Policy
    val first: Int,
    val second: Int,
    val letter: Char,
    // Password
    val password: String,
)

fun PasswordEntry.validPassword(): Boolean =
    (first..second).contains(password.count {
        letter == it
    })

fun PasswordEntry.validPassword2(): Boolean =
    (password[first - 1] == letter) xor (password[second - 1] == letter)
