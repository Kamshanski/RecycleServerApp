package edu.tpu.ruban.shared.user.domain.repository

import edu.tpu.ruban.shared.user.domain.entity.User

interface UserRepository {

    suspend fun getById(userId: Long): User?

    suspend fun getByLogin(login: String): User?

    suspend fun create(user: User)

    suspend fun update(): User

    suspend fun delete()
}