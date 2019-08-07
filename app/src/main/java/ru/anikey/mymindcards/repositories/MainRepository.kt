package ru.anikey.mymindcards.repositories

import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.MainPresenter

class MainRepository(val mPresenter: MainPresenter) {
    private val cards = listOf(
        CardModel("First", "First question", "First answer"),
        CardModel("Second", "Second question", "Second answer"),
        CardModel("Third", "Third question", "Third answer"),
        CardModel("Fourth", "Fourth question", "Fourth answer"),
        CardModel("Fifth", "Fifth question", "Fifth answer")
    )

    fun loadCards() {
        mPresenter.buildList(cards)
    }
}