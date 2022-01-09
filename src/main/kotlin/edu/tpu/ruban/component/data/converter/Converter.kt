package edu.tpu.ruban.component.data.converter

interface Converter<FROM, TO> {

    operator fun invoke(from: FROM): TO
}

inline fun <FROM, TO> FROM.convert(converter: Converter<FROM, TO>) : TO = converter(this)