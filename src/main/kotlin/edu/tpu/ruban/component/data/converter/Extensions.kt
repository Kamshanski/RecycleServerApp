package edu.tpu.ruban.component.data.converter

public inline fun <T, R> T.convert(block: (T) -> R): R = let(block)