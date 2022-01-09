package edu.tpu.ruban.core.plugin.hikari

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import edu.tpu.ruban.component.environment.Config
import edu.tpu.ruban.core.plugin.Plugin
import io.ktor.application.*
import org.jetbrains.exposed.sql.Database
import javax.inject.Inject

class HikariPlugin @Inject constructor(
    val appConfig: Config,
): Plugin {

    override fun configure(application: Application) {
        val config = HikariConfig().apply {
            driverClassName = appConfig.jdcb.driverClassName
            jdbcUrl = appConfig.jdcb.jdbcUrl
            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }
}