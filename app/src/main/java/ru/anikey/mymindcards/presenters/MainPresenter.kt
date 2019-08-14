package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun initCardList() {
        val cards = MainRepository.getCardList()
        viewState.showList(cards)
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

    fun deleteCard(card: CardModel) {
        MainRepository.deleteCard(card)
        viewState.showList(MainRepository.getCardList())
    }

}
