package edu.tpu.ruban.component.data.converter

interface DataConverter<DOMAIN, DATA> {

    fun toData(entity: DOMAIN): DATA

    fun toDomain(model: DATA) : DOMAIN
}