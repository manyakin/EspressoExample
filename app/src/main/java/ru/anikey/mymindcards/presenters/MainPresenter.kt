package ru.anikey.mymindcards.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {

    fun initCardList(context: Context) {
        val cards = MainRepository.getCardList(context)
        viewState.showList(cards)
    }

    fun addButtonPressed() {
        viewState.startAddCardActivity()
    }

    fun startTestPressed() {
        viewState.startTest()
    }

    fun getClickedCard(context: Context, position: Int) {
        val card = MainRepository.getCard(context, position)
        viewState.startEditCardActivity(card, position)
    }

    fun deleteCard(context: Context, card: CardModel) {
        MainRepository.deleteCard(context, card)
        viewState.showList(MainRepository.getCardList(context))
    }

}
