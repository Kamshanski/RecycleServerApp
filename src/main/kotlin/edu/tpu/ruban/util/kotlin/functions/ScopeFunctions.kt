package edu.tpu.ruban.util.kotlin.functions

/**
 * Like apply or run, but returns Unit
 */
public inline fun <T> T.accept(block: T.() -> Unit) {
    this.block()
}

@Suppress("SimplifyBooleanWithConstants")
public inline fun <T> Boolean.onTrue(block: () -> T) : T? =
    if (this == true)
        block()
    else
        null

@Suppress("SimplifyBooleanWithConstants")
public inline fun <T> Boolean.onFalse(block: () -> T) : T? =
    if (this == false)
        block()
    else
        null
