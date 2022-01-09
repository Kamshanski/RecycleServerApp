package edu.tpu.ruban.shared.user.data.datasource

import edu.tpu.ruban.shared.user.data.model.UserModel

interface UserDataSource {

    suspend fun create(userModel: UserModel)

    suspend fun getByLogin(login: String): UserModel?

    suspend fun getBySimilarLogin(login: String): List<UserModel>

    suspend fun getById(userId: Long): UserModel?

    suspend fun delete(userModel: UserModel)
}