package ru.anikey.mymindcards.di

import dagger.Module
import dagger.Provides
import ru.anikey.mymindcards.db.DBHelper

@Module
class DBHelperModule {

    @Provides
    fun providesDBHelper(): DBHelper {
        return DBHelper()
    }
}