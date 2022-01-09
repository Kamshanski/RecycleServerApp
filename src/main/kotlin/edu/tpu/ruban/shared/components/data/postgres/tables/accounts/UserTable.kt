package edu.tpu.ruban.shared.components.data.postgres.tables.accounts

import edu.tpu.ruban.component.data.database.PgTable

object UserTable : PgTable(
    "accounts",
    "user",
) {

    val userId = column("user_id", ignoreInsertion = true)
    val login = column("login")
    val hash = column("hash")
    val admin = column("admin")
    val ban = column("ban")
    // TODO добавить creation_time столбец в таблицу
}