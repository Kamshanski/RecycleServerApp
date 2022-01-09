package edu.tpu.ruban.shared.security.hash.domain.usecase

import edu.tpu.ruban.shared.security.hash.domain.entity.PasswordEncryptor
import javax.inject.Inject

class VerifyPasswordUseCase @Inject constructor(
    private val passwordEncryptor: PasswordEncryptor,
) {

    operator fun invoke(password: String, hash: String): Boolean =
        passwordEncryptor.verifyPassword(password, hash)
}