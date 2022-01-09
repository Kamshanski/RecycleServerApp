package edu.tpu.ruban.shared.user.data.repository

import edu.tpu.ruban.component.data.converter.DataConverter
import edu.tpu.ruban.component.data.converter.convert
import edu.tpu.ruban.shared.user.data.model.UserModel
import edu.tpu.ruban.shared.user.data.datasource.UserDataSource
import edu.tpu.ruban.shared.user.domain.entity.User
import edu.tpu.ruban.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val source: UserDataSource,
    private val userConverter: DataConverter<User, UserModel>,
) : UserRepository {

    override suspend fun getById(userId: Long): User? =
        source.getById(userId)?.convert(userConverter::toDomain)

    override suspend fun getByLogin(login: String): User? =
        source.getByLogin(login)?.convert(userConverter::toDomain)

    override suspend fun create(user: User) {
        val userModel = user.convert(userConverter::toData)
        source.create(userModel)
    }

    override suspend fun update(): User {
        TODO("update in repository Not yet implemented")
    }

    override suspend fun delete() {
        TODO("Not yet implemented")
    }
}