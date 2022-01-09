package edu.tpu.ruban.shared.user.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import edu.tpu.ruban.component.data.converter.DataConverter
import edu.tpu.ruban.component.data.converter.SqlConverter
import edu.tpu.ruban.shared.user.data.model.UserModel
import edu.tpu.ruban.shared.user.data.converter.PgUserModelConverter
import edu.tpu.ruban.shared.user.data.datasource.PgUserDataSource
import edu.tpu.ruban.shared.user.data.datasource.UserDataSource
import edu.tpu.ruban.shared.user.data.repository.UserRepositoryImpl
import edu.tpu.ruban.shared.user.domain.entity.User
import edu.tpu.ruban.shared.user.domain.repository.UserRepository

@Module
interface SharedUserModule {

    companion object {

        @Provides
        fun provideUserModelConverter(): DataConverter<User, UserModel> = PgUserModelConverter

        @Provides
        fun provideSqlUserModelConverter(): SqlConverter<UserModel> = PgUserModelConverter
    }

    @Binds
    fun bindUserDataSource(impl: PgUserDataSource): UserDataSource

    @Binds
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

}