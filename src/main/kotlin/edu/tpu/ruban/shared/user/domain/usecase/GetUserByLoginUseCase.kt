package edu.tpu.ruban.shared.user.domain.usecase

import edu.tpu.ruban.shared.user.domain.entity.User
import edu.tpu.ruban.shared.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByLoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(login: String): User? =
        userRepository.getByLogin(login)

}