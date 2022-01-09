package edu.tpu.ruban.shared.components.data.auth

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.interfaces.Payload
import edu.tpu.ruban.util.kotlin.collections.ClaimsBundle
import io.ktor.auth.*
import io.ktor.auth.jwt.*

interface AuthManager {

    companion object {

        private const val DEFAULT_LIFE_TIME = 15L * 60L * 60L * 1000L // 15h
    }

    fun grantToken(claims: ClaimsBundle, realm: String, lifeTime: Long = DEFAULT_LIFE_TIME): String

    fun buildVerifier(): JWTVerifier

    fun validate(credential: JWTCredential, expectedRealm: String? = null, validator: (Payload) -> Boolean): Principal?

    fun errorHandler(defaultScheme: String, realm: String)
}