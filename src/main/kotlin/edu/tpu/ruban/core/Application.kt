package edu.tpu.ruban.core

import edu.tpu.ruban.core.plugin.Plugin
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import javax.inject.Inject

class Application @Inject constructor(
    private val plugins: Set<@JvmSuppressWildcards Plugin>
) {

    private val engine: ApplicationEngine = embeddedServer(Netty, port = 8082, host = "0.0.0.0") {
        plugins.forEach { plugin -> plugin.configure(this) }
    }

    fun run() : Nothing {
        engine.start(wait = true)
        throw IllegalStateException("Server edu.tpu.ruban.app never ends, but it actually finished.")
    }
}
