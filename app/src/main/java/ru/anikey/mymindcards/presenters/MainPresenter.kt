package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun initCardList() {
        viewState.showList(MainRepository.getCardList())
    }

    fun addButtonPressed() {
        viewState.startAddCardActivity()
    }

    fun startTestPressed() {
        viewState.startTest()
    }

    fun getClickedCard(position: Int) {
        val card = MainRepository.getCard(position)
        viewState.startEditCardActivity(card, position)
    }

    fun deleteCard(position: Int) {
        MainRepository.deleteCard(position)
        viewState.showList(MainRepository.getCardList())
    }

}
