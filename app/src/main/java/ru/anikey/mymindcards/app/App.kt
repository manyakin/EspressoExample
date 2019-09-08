package ru.anikey.mymindcards.app

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import ru.anikey.mymindcards.di.AppComponent
import ru.anikey.mymindcards.di.DaggerAppComponent
import ru.anikey.mymindcards.di.RepositoryModule

class App : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)
        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule())
            .build()
    }
}