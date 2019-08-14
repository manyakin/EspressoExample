package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.AddCardView

@InjectViewState
class AddCardPresenter : MvpPresenter<AddCardView>() {

    fun saveCard(title: String, question: String, answer: String) {
        val card = MainRepository.addCard(title, question, answer)
        viewState.cardSaved(card)
    }

    fun editCard(card: CardModel, title: String, question: String, answer: String) {
        val editedCard = MainRepository.editCard(card, title, question, answer)
        viewState.cardSaved(editedCard)
    }
}