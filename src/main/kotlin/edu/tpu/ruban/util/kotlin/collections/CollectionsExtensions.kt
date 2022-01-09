package edu.tpu.ruban.util.kotlin.collections

public operator fun <T> MutableList<T>.plus(other: MutableList<T>) : MutableList<T> =
    ArrayList<T>(this.size + other.size).apply {
        addAll(this@plus)
        addAll(other)
    }