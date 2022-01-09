package edu.tpu.ruban.shared.user.data.converter

import edu.tpu.ruban.component.data.converter.DataConverter
import edu.tpu.ruban.component.data.converter.SqlConverter
import edu.tpu.ruban.shared.user.data.model.UserModel
import edu.tpu.ruban.shared.components.data.postgres.tables.accounts.UserTable
import edu.tpu.ruban.shared.user.domain.entity.User
import java.sql.ResultSet

object PgUserModelConverter : DataConverter<User, UserModel>, SqlConverter<UserModel> {

    operator fun invoke(entity: User): UserModel = toData(entity)

    operator fun invoke(model: UserModel): User = toDomain(model)

    operator fun invoke(resultSet: ResultSet): List<UserModel> = fromResultSet(resultSet)

    override fun fromResultSet(result: ResultSet): List<UserModel> =
        buildList {
            while (result.next()) {
                val userId = result.getLong(UserTable.userId.name)
                val login = result.getString(UserTable.login.name)
                val hash = result.getString(UserTable.hash.name)
                val isAdmin = result.getBoolean(UserTable.admin.name)
                val isBanned = result.getBoolean(UserTable.ban.name)
                add(UserModel(userId, login, hash, isAdmin, isBanned))
            }
        }


    override fun toData(entity: User): UserModel = UserModel(
        entity.id,
        entity.login,
        entity.hash,
        entity.isAdmin,
        entity.isBanned,
    )

    override fun toDomain(model: UserModel): User = User(
        model.userId,
        model.login,
        model.hash,
        model.isAdmin,
        model.isBanned,
    )
}