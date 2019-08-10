package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.AddCardView

@InjectViewState
class AddCardPresenter : MvpPresenter<AddCardView>() {

    fun saveCard(card: CardModel) {
        MainRepository.addCard(card)
        viewState.cardSaved()
    }

    fun editCard(card: CardModel, position: Int) {
        MainRepository.editCard(card, position)
        viewState.cardSaved()
    }
}