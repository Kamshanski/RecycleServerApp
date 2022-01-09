package edu.tpu.ruban.shared.security.hash.di

import dagger.Module
import dagger.Provides
import edu.tpu.ruban.shared.security.hash.domain.entity.PasswordEncryptor
import edu.tpu.ruban.shared.security.hash.domain.entity.PasswordEncryptorImpl

@Module
interface HashEncryptorModule {

    companion object {

        @Provides
        fun providePasswordEncryptor(): PasswordEncryptor = PasswordEncryptorImpl
    }
}