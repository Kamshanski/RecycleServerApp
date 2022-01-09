package edu.tpu.ruban.component.data.database

import java.sql.ResultSet

interface DatabaseAccess {

    suspend fun <T> queryExpose(block: () -> T): T

    suspend fun <T: Any> executeForResult(sqlQuery: String, transform: (ResultSet) -> T) : T

    suspend fun execute(sqlQuery: String)
}