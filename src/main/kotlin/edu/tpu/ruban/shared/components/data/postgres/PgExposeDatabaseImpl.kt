package edu.tpu.ruban.shared.components.data.postgres

import edu.tpu.ruban.component.data.database.DatabaseAccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.ResultSet
import java.sql.SQLException

object PgExposeDatabaseImpl : DatabaseAccess {

    override suspend fun <T> queryExpose(block: () -> T): T = withContext(Dispatchers.IO) {
        transaction { block() }
    }

    override suspend fun <T: Any> executeForResult(sqlQuery: String, transform: (ResultSet) -> T) : T =
        queryExpose {
            TransactionManager.current().exec(sqlQuery, emptyList(), transform)
                ?: throw SQLException("Null Response")
        }

    override suspend fun execute(sqlQuery: String) {
        queryExpose {
            TransactionManager.current().exec(sqlQuery, emptyList())
        }
    }
}