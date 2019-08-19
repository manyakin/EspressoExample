package ru.anikey.mymindcards.di

import dagger.Module
import dagger.Provides
import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.repositories.MainRepository

@Module
class RepositoryModule {

    @Provides
    fun providesMainRepository(dbWriter: DBWriter, dbReader: DBReader): MainRepository {
        return MainRepository(dbWriter = dbWriter, dbReader = dbReader)
    }
}