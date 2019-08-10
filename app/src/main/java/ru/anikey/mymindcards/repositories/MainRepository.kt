package ru.anikey.mymindcards.repositories

import ru.anikey.mymindcards.models.CardModel

object MainRepository {
    val cards = mutableListOf(
        CardModel("First", "First question", "First answer"),
        CardModel("Second", "Second question", "Second answer"),
        CardModel("Third", "Third question", "Third answer"),
        CardModel("Fourth", "Fourth question", "Fourth answer"),
        CardModel("Fifth", "Fifth question", "Fifth answer")
    )

    fun addCard(card: CardModel) {
        cards.add(card)
    }
}