package edu.tpu.ruban.shared.components.routing.response

import com.google.gson.Gson
import edu.tpu.ruban.component.ui.presentation.model.Result
import edu.tpu.ruban.component.ui.routing.response.ResponseFactory
import io.ktor.content.*
import io.ktor.http.*
import javax.inject.Inject

class JsonResponseFactory @Inject constructor(
    private val gson: Gson,
) : ResponseFactory<Result> {

    companion object {

        private val JSON_CONTENT_TYPE = ContentType.Application.Json.withCharset(Charsets.UTF_8)
    }

    override fun create(result: Result): Any {
        return TextContent(
            text = gson.toJson(result),
            JSON_CONTENT_TYPE
        )
    }
}