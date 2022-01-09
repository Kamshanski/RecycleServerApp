package edu.tpu.ruban.core.plugin.security

import com.auth0.jwt.interfaces.Payload
import edu.tpu.ruban.core.plugin.Plugin
import edu.tpu.ruban.shared.components.data.auth.AuthManager
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import javax.inject.Inject

class SecurityPlugin @Inject constructor(
    private val authManager: AuthManager
) : Plugin {

    companion object {
        private const val USER_ID_CLAIM = "userId"

        const val JWT_AUTH_MAIN = "auth-jwt-main"
    }

    override fun configure(application: Application) {
        application.authentication {
            jwt(JWT_AUTH_MAIN) {
                this.realm = JWT_AUTH_MAIN
                verifier(authManager.buildVerifier())
                validate { credential ->
                    authManager.validate(credential, realm, ::validateOrdinalUser)
                }
                challenge { defaultScheme, realm -> authManager.errorHandler(defaultScheme, realm) }
            }
        }
    }

    private fun validateOrdinalUser(payload: Payload) : Boolean =
        payload.getClaim(USER_ID_CLAIM).asString().isNullOrBlank()
}