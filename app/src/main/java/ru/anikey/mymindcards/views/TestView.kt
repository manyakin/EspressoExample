package ru.anikey.mymindcards.views

import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface TestView : MvpView {
    fun showCard(card: CardModel)
    fun showAnswer()
    fun showResult(trueAnswers: String, falseAnswers: String, result: String)
}