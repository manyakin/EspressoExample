package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.app.App
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.AddCardView
import javax.inject.Inject

@InjectViewState
class AddCardPresenter : MvpPresenter<AddCardView>() {

    @Inject
    lateinit var mainRepository: MainRepository

    init {
        App.appComponent.inject(this@AddCardPresenter)
    }

    fun saveCard(title: String, question: String, answer: String) {
        val card = mainRepository.addCard(title, question, answer)
        viewState.cardSaved(card)
    }

    fun editCard(card: CardModel, title: String, question: String, answer: String) {
        val editedCard = mainRepository.editCard(card, title, question, answer)
        viewState.cardSaved(editedCard)
    }
}