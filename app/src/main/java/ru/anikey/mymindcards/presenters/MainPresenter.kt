package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun initCardList() {
        viewState.showList(MainRepository.cards)
    }

    fun addButtonPressed() {
        viewState.startAddCardActivity()
    }

}
