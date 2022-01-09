package edu.tpu.ruban.shared.components.data.auth.di

import dagger.Binds
import dagger.Module
import edu.tpu.ruban.shared.components.data.auth.AuthManagerImpl
import edu.tpu.ruban.shared.components.data.auth.AuthManager
import javax.inject.Singleton

@Module
interface AuthModule {

    @Binds
    @Singleton
    fun bindAuthManager(impl: AuthManagerImpl): AuthManager
}