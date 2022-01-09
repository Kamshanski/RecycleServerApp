package edu.tpu.ruban.component.ui.routing.extensions

import io.ktor.application.*
import io.ktor.util.pipeline.*

typealias RouteHandler = PipelineInterceptor<Unit, ApplicationCall>