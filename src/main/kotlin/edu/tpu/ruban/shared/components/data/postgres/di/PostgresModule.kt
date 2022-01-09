package edu.tpu.ruban.shared.components.data.postgres.di

import dagger.Module
import dagger.Provides
import edu.tpu.ruban.component.data.database.DatabaseAccess
import edu.tpu.ruban.shared.components.data.postgres.PgExposeDatabaseImpl

@Module
interface PostgresModule {

    companion object {

        @Provides
        fun bindDatabaseAccess(): DatabaseAccess = PgExposeDatabaseImpl
    }
}