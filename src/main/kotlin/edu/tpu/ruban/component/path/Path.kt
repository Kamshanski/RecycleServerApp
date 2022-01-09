package edu.tpu.ruban.component.path

import edu.tpu.ruban.util.kotlin.collections.plus

open class Path private constructor(
    val delimiter: String
) {

    constructor(path: String?, delimiter: String) : this(delimiter) {
        val steps = path?.split(delimiter)
        if (steps != null) {
            chain.addAll(steps)
        }
    }

    private constructor(chain: MutableList<String>, delimiter: String) : this(delimiter) {
        this.chain = chain
    }

    private var chain: MutableList<String> = mutableListOf()



    operator fun plus(subPath: Path): Path =
        Path(
            chain = chain + subPath.chain,
            delimiter = subPath.delimiter
        )

    operator fun plus(subPath: String): Path = this + Path(subPath, delimiter)

    operator fun String.plus(subPath: Path): Path = Path(this, subPath.delimiter) + subPath

    override fun toString(): String =
        chain.joinToString(separator = delimiter)

    fun toString(transformer: (String) -> String) : String =
        chain.joinToString(separator = delimiter, transform = transformer)


    val size: Int
        get() = chain.size
}