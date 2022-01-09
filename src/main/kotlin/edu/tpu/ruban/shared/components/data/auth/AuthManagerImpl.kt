package edu.tpu.ruban.shared.components.data.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.Claim
import com.auth0.jwt.interfaces.Payload
import edu.tpu.ruban.component.environment.Config
import edu.tpu.ruban.util.kotlin.collections.ClaimsBundle
import edu.tpu.ruban.shared.error.domain.entity.DomainErrorType
import edu.tpu.ruban.shared.error.domain.entity.DomainException
import edu.tpu.ruban.shared.error.domain.entity.RequestException
import edu.tpu.ruban.util.kotlin.functions.onTrue
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import java.util.*
import javax.inject.Inject

class AuthManagerImpl @Inject constructor(
    val config: Config
) : AuthManager {

    companion object {

        const val REALM_CLAIM = "edu.tpu.ruban.core.plugin.security.data.REALM_CLAIM"
    }


    // Expiration is checked automatically
    private val verifierBase = JWT
        .require(Algorithm.HMAC512(config.jwt.secret))
        .withAudience(config.jwt.audience)
        .withSubject(config.jwt.subject)
        .withIssuer(config.jwt.issuer)


    override fun grantToken(claims: ClaimsBundle, realm: String, lifeTime: Long): String {
        checkClaims(claims)
        require(lifeTime > 0L) {
            DomainException(DomainErrorType.TOKEN_MALFORMATION, "Token life time must be > 0")
        }

        return JWT.create()
            .withAudience(config.jwt.audience)
            .withIssuer(config.jwt.issuer)
            .withSubject(config.jwt.subject)
            .withClaim(claims)
            .withClaim(REALM_CLAIM, realm)
            .withExpiresAt(Date(System.currentTimeMillis() + lifeTime))
            .sign(Algorithm.HMAC512(config.jwt.secret))
    }


    private fun checkClaims(claims: ClaimsBundle) {
        if (claims.getAny(REALM_CLAIM) != null) {
            throw DomainException(DomainErrorType.TOKEN_MALFORMATION, "Claim name cannot be $REALM_CLAIM")
        }
    }


    override fun buildVerifier(): JWTVerifier = verifierBase.build()


    override fun validate(credential: JWTCredential, expectedRealm: String?, validator: (Payload) -> Boolean): Principal? {
        val payload = credential.payload

        val receivedRealm = payload.realm
        if (receivedRealm.isNull) {
            throw RequestException("Corrupted token")
        }

        if (expectedRealm != null) {
            if (receivedRealm.asString() == expectedRealm) {
                return null
            }
        }

        return validator(payload).onTrue { JWTPrincipal(payload) }
    }


    override fun errorHandler(defaultScheme: String, realm: String) {
        print("Cannot login into $realm with scheme $defaultScheme")
    }


    private fun JWTCreator.Builder.withClaim(bundle: ClaimsBundle): JWTCreator.Builder {
        bundle.forEach { key, value ->
            when (value) {
                is Boolean -> withClaim(key, value)
                is Int -> withClaim(key, value)
                is Long -> withClaim(key, value)
                is Double -> withClaim(key, value)
                is ArrayList<*> -> withClaim(key, value)
            }
        }

        return this
    }


    val Payload.realm: Claim
        get() = getClaim(REALM_CLAIM)
}