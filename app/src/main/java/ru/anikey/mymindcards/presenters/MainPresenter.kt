package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.app.App
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    @Inject
    lateinit var mainRepository: MainRepository

    init {
        App.appComponent.inject(this@MainPresenter)
    }

    fun initCardList() {
        val cards = mainRepository.getCardList()
        viewState.showList(cards)
    }

    fun addButtonPressed() {
        viewState.startAddCardActivity()
    }

    fun startTestPressed() {
        val cards = mainRepository.getCardList()
        if (cards.size >= 1) {
            viewState.startTest()
        } else {
            viewState.showError(R.string.main_no_cards_for_test)
        }
    }

    fun getClickedCard(position: Int) {
        val card = mainRepository.getCard(position)
        viewState.startEditCardActivity(card, position)
    }

    fun deleteCard(card: CardModel) {
        mainRepository.deleteCard(card)
    }

}
