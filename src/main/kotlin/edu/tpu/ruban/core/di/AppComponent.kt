package edu.tpu.ruban.core.di

import dagger.Component
import edu.tpu.ruban.core.Application
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    fun application(): Application

    @Component.Factory
    interface Factory {
        fun create() : AppComponent
    }
}