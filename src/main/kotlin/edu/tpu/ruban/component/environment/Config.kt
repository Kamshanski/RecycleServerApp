package edu.tpu.ruban.component.environment

import java.lang.System.getenv

// TODO: лучше перенести в shared.config и перенести bind из appModule в SharedAppConfigModule. И переименовать класс в AppConfig
object Config {

    object JWT {
        val secret = getenv("JWT_SECRET")
        val issuer = getenv("JWT_ISSUER")
        val subject = getenv("JWT_SUBJECT")
        val audience = getenv("JWT_AUDIENCE")
    }
    val jwt = JWT

    object JDCB {
        val driverClassName = getenv("JDBC_DRIVER")
        val jdbcUrl = getenv("DATABASE_URL")
    }
    val jdcb = JDCB

}