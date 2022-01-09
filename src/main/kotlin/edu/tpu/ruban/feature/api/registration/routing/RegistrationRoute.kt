package edu.tpu.ruban.feature.api.registration.routing

import edu.tpu.ruban.component.ktor.routing.route.BaseRoute
import edu.tpu.ruban.component.path.Path
import edu.tpu.ruban.shared.components.routing.response.JsonResponseFactory
import edu.tpu.ruban.component.route.Paths
import edu.tpu.ruban.component.ui.presentation.model.Result
import edu.tpu.ruban.component.ui.routing.extensions.RouteHandler
import edu.tpu.ruban.feature.api.registration.presentation.model.RegistrationInputModel
import edu.tpu.ruban.feature.api.registration.presentation.RegistrationViewModel
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import javax.inject.Inject

// TODO подумать разделить назначатель route'ов и handler'ы, чтобы назначатель был singleton, а handler'ы создавались на каждый вызов и имели call в качестве поля

// TODO заменить JsonResponceFactory на интерфейс
class RegistrationRoute @Inject constructor(
    private val viewModel: RegistrationViewModel,
    private val responseFactory: JsonResponseFactory,
): BaseRoute() {

    override val pathChain: Path = Paths.API_PATH + "register"

    override fun configureRoutes(routing: Routing) {
        routing.route(path) {
            post(postHandler)
        }
    }

    private val postHandler: RouteHandler = {
        val input = call.receive<RegistrationInputModel>()
        val action = viewModel.handleRegisterPost(input)
        call.proceed(action)
    }


    override suspend fun respondCall(call: ApplicationCall, payload: Result) {
        call.respond(responseFactory.create(payload))
    }

    override suspend fun redirect(call: ApplicationCall, path: String) {
        TODO("not implemented")
    }
}