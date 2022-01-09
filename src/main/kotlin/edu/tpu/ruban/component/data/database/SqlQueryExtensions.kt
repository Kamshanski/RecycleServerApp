package edu.tpu.ruban.component.data.database

typealias ConditionsList = ArrayList<String>

inline fun String.asPgString() = pgString(this)
inline fun String.asStartPgTemplate() = startPgString(this)
inline fun String.asEndPgTemplate() = endPgString(this)
inline fun String.asIncludePgTemplate() = includedPgString(this)

inline fun pgColumn(column: String): String = "\"$column\""
inline fun pgString(value: String): String = "'$value'"
inline fun startPgString(value: String): String = "'$value%'"
inline fun endPgString(value: String): String = "'%$value'"
inline fun includedPgString(value: String): String = "'%$value%'"



//fun String.asPgColumn() : String {
//    val steps = this.split('.')
//
//    return StringBuilder().apply {
//        for (step in steps) {
//            if (step.first() != '"') {
//                append('\"')
//            }
//
//            append(step)
//
//            if (step.last() != '"') {
//                append('\"')
//            }
//
//            append(".")
//        }
//
//        if (length > 0) {
//            removeEnding(".")
//        }
//    }.toString()
//}
//
//fun selectAllFrom(tableName: String) : String = """SELECT * FROM $tableName"""
//
//fun insertSingleValueInto(tableName: String, columns: String, vararg values: String): String =
//    insertValuesInto(tableName, columns, values) { value ->
//        append(value.asPgString())
//    }
//
//
//inline fun <T> insertValuesInto(tableName: String, columns: String, values: Array<T>, transformer: StringBuilder.(T) -> Unit) : String =
//    StringBuilder().apply {
//        append("""INSERT INTO """)
//        append(tableName)
//        append('(')
//        append(columns)
//        append(')')
//        append(" VALUES ")
//        for (value in values) {
//            append('(')
//            transformer(value)
//            append(')')
//            append(", ")
//        }
//        removeEnding(", ")
//    }.toString()
//
//inline fun <T> insertValuesInto(tableName: String, columns: String, values: List<T>, transformer: StringBuilder.(T) -> Unit) : String =
//    StringBuilder().apply {
//        append("""INSERT INTO """)
//        append(tableName)
//        append('(')
//        append(columns)
//        append(')')
//        append(" VALUES ")
//        for (value in values) {
//            append('(')
//            transformer(value)
//            append(')')
//            append(", ")
//        }
//        removeEnding(", ")
//    }.toString()
//
//
//fun ConditionsList.addEquals(column: String, stringValue: String, formatColumn: Boolean = false) {
//    add(""" ${ if (formatColumn) column.asPgColumn() else column } = ${stringValue.asPgString()} """)
//}
//
//fun ConditionsList.addEquals(column: String, boolValue: Boolean, formatColumn: Boolean = false) {
//    add(""" ${ if (formatColumn) column.asPgColumn() else column } = $boolValue """)
//}
//
//fun ConditionsList.addEquals(column: String, numberValue: Int, formatColumn: Boolean = false) {
//    add(""" ${ if (formatColumn) column.asPgColumn() else column } = $numberValue """)
//}
//
//
//fun ConditionsList.addLike(column: String, stringValue: String, formatColumn: Boolean = false) {
//    add(""" ${ if (formatColumn) column.asPgColumn() else column } LIKE $stringValue """)
//}
//
//
//
//fun StringBuilder.appendWhere(conditions: List<String>) : StringBuilder {
//    if (conditions.isNotEmpty()) {
//        append(" WHERE ")
//        conditions.joinTo(this, separator = " AND ", prefix = " ", postfix = " ")
//    }
//    return this
//}
//
//fun StringBuilder.appendLimitOrNone(limit: Int) : StringBuilder {
//    if (limit > 0) {
//        append(" LIMIT ")
//        append(limit)
//    }
//    return this
//}