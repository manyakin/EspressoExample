package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.R
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
        val cards = MainRepository.getCardList()
        if (cards.size >= 1) {
            viewState.startTest()
        } else {
            viewState.showError(R.string.main_no_cards_for_test)
        }
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
