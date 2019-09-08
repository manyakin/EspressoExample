package ru.anikey.mymindcards.di

import dagger.Component
import ru.anikey.mymindcards.presenters.AddCardPresenter
import ru.anikey.mymindcards.presenters.MainPresenter
import ru.anikey.mymindcards.presenters.TestPresenter
import javax.inject.Singleton

@Component(modules = [RepositoryModule::class])
@Singleton
interface AppComponent {

    // Presenters
    fun inject(mainPresenter: MainPresenter)

    fun inject(addCardPresenter: AddCardPresenter)

    fun inject(testPresenter: TestPresenter)
}