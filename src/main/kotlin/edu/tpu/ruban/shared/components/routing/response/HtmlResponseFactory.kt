package edu.tpu.ruban.shared.components.routing.response

import edu.tpu.ruban.component.ui.routing.response.ResponseFactory
import io.ktor.freemarker.*
import javax.inject.Inject

class HtmlResponseFactory @Inject constructor(): ResponseFactory<String> {

    override fun create(
        result: String
    ): Any {
        val path = result ?: error("Path to HTML file cannot be null")
        return FreeMarkerContent(template = path, model = result)
    }
}