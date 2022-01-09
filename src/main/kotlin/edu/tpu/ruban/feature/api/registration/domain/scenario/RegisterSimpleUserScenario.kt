package edu.tpu.ruban.feature.api.registration.domain.scenario

import edu.tpu.ruban.shared.error.domain.entity.DomainErrorType
import edu.tpu.ruban.shared.error.domain.entity.DomainException
import edu.tpu.ruban.shared.security.hash.domain.usecase.EncryptUserPasswordUseCase
import edu.tpu.ruban.shared.user.domain.entity.Credentials
import edu.tpu.ruban.shared.user.domain.entity.User
import edu.tpu.ruban.shared.user.domain.usecase.CreateUserUseCase
import edu.tpu.ruban.shared.user.domain.usecase.GetUserByLoginUseCase
import javax.inject.Inject

class RegisterSimpleUserScenario @Inject constructor(
    private val getUserByLoginUseCase: GetUserByLoginUseCase,
    private val encryptPasswordUseCase: EncryptUserPasswordUseCase,
    private val createUserUseCase: CreateUserUseCase,
) {
    suspend operator fun invoke(credentials: Credentials) {
        val existingUser = getUserByLoginUseCase(credentials.login)

        if (existingUser != null) {
            throw DomainException(DomainErrorType.USER_ALREADY_EXISTS, credentials.login)
        }

        val hashedPassword = encryptPasswordUseCase(credentials.password)

        val newUser = User(
            id = -1,
            login = credentials.login,
            hash = hashedPassword,
            isAdmin = false,
            isBanned = false,
        )

        createUserUseCase(newUser)
    }
}