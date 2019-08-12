package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.TestView
import java.math.RoundingMode
import java.text.DecimalFormat

@InjectViewState
class TestPresenter : MvpPresenter<TestView>() {
    private val cardList = MainRepository.getCardList().shuffled()
    private var cardCount = 0
    private var trueAnswers = 0
    private var falseAnswers = 0

    fun getCard() {
        if (cardCount < cardList.size) {
            viewState.showCard(cardList[cardCount])
        } else {
            cardCount = 0
            val result = (trueAnswers.toDouble() / cardList.size) * 100
            viewState.showResult(trueAnswers.toString(), falseAnswers.toString(), roundResult(result))
        }
    }

    fun setCount() {
        cardCount += 1
        viewState.showAnswer()
    }

    fun answerIsTrue() {
        trueAnswers += 1
        getCard()
    }

    fun answerIsFalse() {
        falseAnswers += 1
        getCard()
    }

    private fun roundResult(result: Double): String {
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        return df.format(result)
    }
}