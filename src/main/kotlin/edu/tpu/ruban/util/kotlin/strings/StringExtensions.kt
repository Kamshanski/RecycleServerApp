package edu.tpu.ruban.util.kotlin.strings

fun StringBuilder.contains(what: String, fromIndex: Int) : Boolean {
    return indexOf(what, fromIndex) == 0
}

fun StringBuilder.endingEquals(ending: String) : Boolean =
    contains(ending, length - ending.length)

fun StringBuilder.removeEnding(ending: String) {
    if (length >= ending.length && endingEquals(ending)) {
        setLength(length - ending.length)
    }
}