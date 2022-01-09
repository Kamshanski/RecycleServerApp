package edu.tpu.ruban.shared.security.hash.domain.usecase

import edu.tpu.ruban.shared.security.hash.domain.entity.PasswordEncryptor
import javax.inject.Inject

class EncryptUserPasswordUseCase @Inject constructor(
    private val passwordEncryptor: PasswordEncryptor
) {

    operator fun invoke(password: String): String =
        passwordEncryptor.passwordHash(password)

}