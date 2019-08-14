package ru.anikey.mymindcards.views

import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface AddCardView : MvpView {
    fun cardSaved(card: CardModel)
}