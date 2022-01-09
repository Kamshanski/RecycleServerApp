package edu.tpu.ruban.shared.components.routing.response.di

import dagger.Binds
import dagger.Module
import edu.tpu.ruban.shared.components.routing.response.HtmlResponseFactory
import edu.tpu.ruban.shared.components.routing.response.JsonResponseFactory

@Module
interface ResponseModule {

    @Binds
    fun bindHtmlResponseFactory(impl: HtmlResponseFactory) : HtmlResponseFactory

    @Binds
    fun bindJsonResponseFactory(impl: JsonResponseFactory) : JsonResponseFactory
}