package edu.tpu.ruban.shared.user.data.datasource

import edu.tpu.ruban.component.data.converter.SqlConverter
import edu.tpu.ruban.component.data.database.DatabaseAccess
import edu.tpu.ruban.component.data.database.asIncludePgTemplate
import edu.tpu.ruban.component.data.database.asPgString
import edu.tpu.ruban.shared.user.data.model.UserModel
import edu.tpu.ruban.shared.components.data.postgres.tables.accounts.UserTable
import javax.inject.Inject

class PgUserDataSource @Inject constructor(
    private val database: DatabaseAccess,
    private val userModelConverter: SqlConverter<UserModel>,
) : UserDataSource {

    override suspend fun create(userModel: UserModel) {
        with(userModel) {
            database.execute(
                """ 
                    INSERT INTO ${UserTable.fullName} (${UserTable.insertColumns}) VALUES
                    (${login.asPgString()}, ${hash.asPgString()}, $isAdmin, $isBanned)
                """
            )
        }
    }

    override suspend fun getByLogin(login: String): UserModel? {
        return database.executeForResult(
            """
               SELECT * FROM ${UserTable.fullName}
               WHERE ${UserTable.login} = ${login.asPgString()}
            """,
            userModelConverter::fromResultSet
        ).firstOrNull()
    }

    override suspend fun getBySimilarLogin(login: String): List<UserModel> {
        return database.executeForResult(
            """
               SELECT * FROM ${UserTable.fullName}
               WHERE ${UserTable.login} LIKE ${login.asIncludePgTemplate()}
            """,
            userModelConverter::fromResultSet
        )
    }

    override suspend fun getById(userId: Long): UserModel? {
        return database.executeForResult(
            """
               SELECT * FROM ${UserTable.fullName}
               WHERE ${UserTable.userId} = $userId
            """,
            userModelConverter::fromResultSet
        ).firstOrNull()
    }

    override suspend fun delete(userModel: UserModel) {
        TODO("User deletion is not implemented yet")
    }
}