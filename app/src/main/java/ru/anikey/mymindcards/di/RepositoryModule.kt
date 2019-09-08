package ru.anikey.mymindcards.di

import dagger.Module
import dagger.Provides
import ru.anikey.mymindcards.db.DBHelper
import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.repositories.IMainRepository
import ru.anikey.mymindcards.repositories.MainRepository

@Module
class RepositoryModule {

    @Provides
    fun providesMainRepository(dbWriter: DBWriter, dbReader: DBReader): IMainRepository {
        return MainRepository(dbWriter = dbWriter, dbReader = dbReader)
    }

    @Provides
    fun providesDBWriter(dbHelper: DBHelper): DBWriter {
        return DBWriter(dbHelper = dbHelper)
    }

    @Provides
    fun providesDBReader(dbHelper: DBHelper): DBReader {
        return DBReader(dbHelper = dbHelper)
    }

    @Provides
    fun providesDBHelper(): DBHelper {
        return DBHelper()
    }
}