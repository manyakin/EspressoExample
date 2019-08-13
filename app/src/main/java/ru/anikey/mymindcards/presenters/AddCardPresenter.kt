package ru.anikey.mymindcards.presenters

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.AddCardView

@InjectViewState
class AddCardPresenter : MvpPresenter<AddCardView>() {

    fun saveCard(context: Context, title: String, question: String, answer: String) {
        MainRepository.addCard(context, title, question, answer)
        viewState.cardSaved()
    }

    fun editCard(context: Context, card: CardModel, title: String, question: String, answer: String) {
        MainRepository.editCard(context, card, title, question, answer)
        viewState.cardSaved()
    }
}