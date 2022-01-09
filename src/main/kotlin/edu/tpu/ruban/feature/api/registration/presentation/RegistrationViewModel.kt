package edu.tpu.ruban.feature.api.registration.presentation

import edu.tpu.ruban.component.state.ProceedAction
import edu.tpu.ruban.component.state.ProceedAction.*
import edu.tpu.ruban.component.ui.presentation.BaseViewModel
import edu.tpu.ruban.feature.api.registration.domain.scenario.RegisterSimpleUserScenario
import edu.tpu.ruban.feature.api.registration.presentation.model.RegistrationInputModel
import edu.tpu.ruban.feature.api.registration.presentation.model.RegistrationOutputModel
import edu.tpu.ruban.shared.error.routing.ExceptionConverter
import edu.tpu.ruban.shared.user.domain.entity.Credentials
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerSimpleUserScenario: RegisterSimpleUserScenario,
    private val errorConverter: ExceptionConverter,
) : BaseViewModel() {

    suspend fun handleRegisterPost(input: RegistrationInputModel): ProceedAction =
        try {
            val credentials = extractCredentials(input)

            registerSimpleUserScenario(credentials)

            Return(success(RegistrationOutputModel()))
        } catch (error: Throwable) {
            Return(error(errorConverter(error)))
        }

    // TODO вынести в отдельный конвертер
    private fun extractCredentials(params: RegistrationInputModel): Credentials = Credentials(
        login = params.login,
        password = params.password,
    )
}