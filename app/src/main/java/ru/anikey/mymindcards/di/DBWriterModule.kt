package ru.anikey.mymindcards.di

import dagger.Module
import dagger.Provides
import ru.anikey.mymindcards.db.DBHelper
import ru.anikey.mymindcards.db.DBWriter

@Module
class DBWriterModule {

    @Provides
    fun providesDBWriter(dbHelper: DBHelper): DBWriter {
        return DBWriter(dbHelper = dbHelper)
    }
}