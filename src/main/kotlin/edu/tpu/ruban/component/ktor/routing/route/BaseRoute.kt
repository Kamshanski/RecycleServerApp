package edu.tpu.ruban.component.ktor.routing.route

import edu.tpu.ruban.component.path.Path
import edu.tpu.ruban.component.state.ProceedAction
import edu.tpu.ruban.component.ui.presentation.model.Result
import edu.tpu.ruban.component.ui.routing.response.ResponseFactory
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

abstract class BaseRoute: Route {

    abstract val pathChain: Path

    val path: String
        get() = pathChain.toString()

    private fun injectHeaders(call: ApplicationCall, headers: Map<String, String>) {
        for ((header, value) in headers) {
            if (header != HttpHeaders.ContentType) {
                call.response.headers.append(header, value)
            }
        }
    }

    protected suspend fun ApplicationCall.proceed(action: ProceedAction) {
        when (action) {
            is ProceedAction.Return -> respondCall(this, action.payload)
            is ProceedAction.Redirect -> redirect(this, "")
        }
    }

    abstract suspend fun respondCall(call: ApplicationCall, payload: Result)

    abstract suspend fun redirect(call: ApplicationCall, path: String)





//////NOT NOW
//    protected suspend inline fun PipelineContext<Unit, ApplicationCall>.safeHttpMethod(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
//        try {
//            body(Unit)
//        } catch (exception: Exception) {
//            handleError(exception)
//        }
//    }
//
//    protected abstract fun handleError(error: Exception)
//
//    @ContextDsl
//    protected inline fun KtorRoute.safeGet(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
//        get {
//            safeHttpMethod(body)
//        }
//    }
//
//    @ContextDsl
//    protected inline fun KtorRoute.safePost(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
//        post {
//            safeHttpMethod(body)
//        }
//    }
//
//    @ContextDsl
//    protected inline fun KtorRoute.safeDelete(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
//        delete {
//            safeHttpMethod(body)
//        }
//    }
//
//    @ContextDsl
//    protected inline fun KtorRoute.safePatch(crossinline body: PipelineInterceptor<Unit, ApplicationCall>) {
//        patch {
//            safeHttpMethod(body)
//        }
//    }
}