package ru.anikey.mymindcards.di

import dagger.Module
import dagger.Provides
import ru.anikey.mymindcards.db.DBHelper
import ru.anikey.mymindcards.db.DBReader

@Module
class DBReaderModule {

    @Provides
    fun providesDBReader(dbHelper: DBHelper): DBReader {
        return DBReader(dbHelper = dbHelper)
    }
}