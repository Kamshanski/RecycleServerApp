package edu.tpu.ruban.component.data.database

import edu.tpu.ruban.component.path.DotPath
import edu.tpu.ruban.component.path.Path

/**
 * Class with table name, schema and columns. Values' syntax and spelling are not checked with!
 * @property rawSchema table schema path as `some.schema.path`
 * @property rawName table name
 */
abstract class PgTable(
    private val rawSchema: String,
    private val rawName: String,
) {

    /**
     * Combines all columns of the table. They are automatically included here during column creation via [column] function.
     * Columns with the same name are skipped as they're forbidden
     */
    private val columnsList = ArrayList<PgColumn>()

    /**
     * Combines columns that can be used in INSERT operation. For example, id column with serial type can be skipped from this list.
     * Columns with the same name are skipped as they're forbidden
     */
    private val insertColumnsList = ArrayList<PgColumn>()

    /**
     * String of columns like
     * @sample `col1, col2, col3`
     */
    private var cachedColumns: String? = null

    /**
     * String of columns like for insert operation
     * @sample `col1, col2, col3`
     */
    private var cachedInsertColumns: String? = null

    /**
     * Parsed [Path] of [rawSchema]
     */
    private val rawSchemaPath = DotPath(rawSchema)

    /**
     * Formatted string of schema path
     * @sample `"some"."schema"."path"`
     */
    val schema = rawSchemaPath.toString { "\"$it\"" }

    /**
     * [Path] combination of schema [Path] and table name
     */
    private val rawFullName = rawSchemaPath + rawName

    /**
     * Formatted string of schema path and table name.
     * @sample `"some"."schema"."path"."table_name"`
     */
    val fullName = rawFullName.toString { "\"$it\"" }

    /**
     * Unformatted table name
     */
    val simpleName = "\"$rawName\""

    /**
     * List of columns. Uses [cachedColumns] storage for string value. Can be recomputed with [recomputeColumnsList]
     * @sample `col1, col2, col3`
     */
    val columns: String
        get() {
            if (cachedColumns == null) {
                recomputeColumnsList()
            }
            return cachedColumns!!
        }

    /**
     * List of columns. Uses [cachedColumns] storage for string value. Can be recomputed with [recomputeColumnsList]
     * @sample `col1, col2, col3`
     */
    val insertColumns: String
        get() {
            if (cachedInsertColumns == null) {
                recomputeColumnsList()
            }
            return cachedInsertColumns!!
        }

    /**
     * Creates table column with name [name] and adds it to [columnsList]
     */
    protected fun column(name: String, ignoreInsertion: Boolean = false): PgColumn {
        val column = PgColumn(name)

        if (!columnsList.contains(column)) {
            columnsList.add(column)
        }
        if (!ignoreInsertion && !insertColumnsList.contains(column)) {
            insertColumnsList.add(column)
        }

        return column
    }

    /**
     * Create string list of all table columns
     */
    fun recomputeColumnsList() {
        cachedColumns = columnsList.joinToString(separator = ", ")
        cachedInsertColumns = insertColumnsList.joinToString(separator = ", ")
    }

    /**
     * Handy class of column
     * @property name Simple name of column
     */
    inner class PgColumn(
        val name: String,
    ) {

        /**
         * Full [Path] to column name
         */
        private val fullNamePath = rawSchemaPath + name

        /**
         * Formatted string of full [Path] to column name
         * @sample `"some"."schema"."path"."table_name"."column1"`
         */
        val full: String = fullNamePath.toString { "\"$it\"" }

        /**
         * Makes use of column safe in string templating and [StringBuilder]
         */
        override fun toString(): String = name

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as PgColumn

            if (name != other.name) return false
            if (full != other.full) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + full.hashCode()
            return result
        }
    }
}