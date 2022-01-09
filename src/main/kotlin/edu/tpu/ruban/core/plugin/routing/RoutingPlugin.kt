package edu.tpu.ruban.core.plugin.routing

import edu.tpu.ruban.component.ktor.routing.route.Route
import edu.tpu.ruban.core.plugin.Plugin
import io.ktor.application.*
import io.ktor.routing.*
import javax.inject.Inject

class RoutingPlugin @Inject constructor(
    val routes: Set<@JvmSuppressWildcards Route>,
): Plugin {

    override fun configure(application: Application) {
        routes.forEach { route ->
            application.routing(route::configureRoutes)
        }
    }
}