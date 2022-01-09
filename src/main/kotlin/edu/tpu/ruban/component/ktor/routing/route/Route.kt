package edu.tpu.ruban.component.ktor.routing.route

import io.ktor.routing.*

interface Route {

    abstract fun configureRoutes(routing: Routing)

}