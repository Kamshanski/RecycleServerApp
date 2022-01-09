package edu.tpu.ruban.feature.api.registration.di

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import edu.tpu.ruban.component.ktor.routing.route.Route
import edu.tpu.ruban.feature.api.registration.routing.RegistrationRoute

@Module
interface RegistrationModule {
    
    @Binds
    @IntoSet
    fun bindRegistrationRoute(impl: RegistrationRoute) : Route
}