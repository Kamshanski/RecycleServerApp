package edu.tpu.ruban.core.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import edu.tpu.ruban.component.environment.Config
import edu.tpu.ruban.core.plugin.Plugin
import edu.tpu.ruban.feature.api.registration.di.RegistrationModule
import edu.tpu.ruban.core.plugin.hikari.HikariPlugin
import edu.tpu.ruban.core.plugin.routing.RoutingPlugin
import edu.tpu.ruban.core.plugin.security.SecurityPlugin
import edu.tpu.ruban.shared.components.data.auth.di.AuthModule
import edu.tpu.ruban.core.plugin.serialization.SerializationPlugin
import edu.tpu.ruban.shared.components.data.postgres.di.PostgresModule
import edu.tpu.ruban.shared.security.hash.di.HashEncryptorModule
import edu.tpu.ruban.shared.user.di.SharedUserModule
import javax.inject.Singleton

@Module(
    includes = [
        // Shared Components
        PostgresModule::class,
        AuthModule::class,

        // Shared features
        HashEncryptorModule::class,
        SharedUserModule::class,

        // Features
        RegistrationModule::class,

    ],

    subcomponents = [

    ],
)
interface AppModule {

    companion object {

        @Provides
        fun provideApplicationConfig() = Config

        @Provides
        @Singleton
        fun provideGson(

        ): Gson = GsonBuilder().apply {

        }.create()
    }


    @Binds
    @IntoSet
    @Singleton
    fun bindRoutingPlugin(impl: RoutingPlugin): Plugin

    @Binds
    @IntoSet
    @Singleton
    fun bindSecurityPlugin(impl: SecurityPlugin): Plugin

    @Binds
    @IntoSet
    @Singleton
    fun bindSerializationPlugin(impl: SerializationPlugin): Plugin

    @Binds
    @IntoSet
    @Singleton
    fun bindDatabasePlugin(impl: HikariPlugin): Plugin
}