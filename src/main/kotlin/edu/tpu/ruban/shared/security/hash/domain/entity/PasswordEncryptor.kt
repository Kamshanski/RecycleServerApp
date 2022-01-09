package edu.tpu.ruban.shared.security.hash.domain.entity

import org.mindrot.jbcrypt.BCrypt

interface PasswordEncryptor {

    fun passwordHash(pwd: String) : String

    fun verifyPassword(pwd: String, hash: String) : Boolean
}

object PasswordEncryptorImpl : PasswordEncryptor {

    override fun passwordHash(pwd: String): String {
        return BCrypt.hashpw(pwd, BCrypt.gensalt())
    }

    override fun verifyPassword(pwd: String, hash: String): Boolean {
        return BCrypt.checkpw(pwd, hash)
    }
}