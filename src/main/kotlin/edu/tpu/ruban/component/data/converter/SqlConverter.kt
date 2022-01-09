package edu.tpu.ruban.component.data.converter

import java.sql.ResultSet

interface SqlConverter<DATA> {

    fun fromResultSet(result: ResultSet): List<DATA>
}

inline fun <DATA> ResultSet.convert(converter: SqlConverter<DATA>) : List<DATA> = converter.fromResultSet(this)