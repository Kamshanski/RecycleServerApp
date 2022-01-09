package edu.tpu.ruban.core.plugin.serialization

import com.google.gson.Gson
import edu.tpu.ruban.core.plugin.Plugin
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import javax.inject.Inject

class SerializationPlugin @Inject constructor(
    private val gson: Gson
): Plugin {

    override fun configure(application: Application) {
        application.install(ContentNegotiation) {
            val converter = GsonConverter(gson)
            register(ContentType.Application.Json, converter)
        }
    }
}