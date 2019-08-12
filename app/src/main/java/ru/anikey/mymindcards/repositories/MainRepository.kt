package ru.anikey.mymindcards.repositories

import ru.anikey.mymindcards.models.CardModel

object MainRepository {
    private val cards = mutableListOf(
        CardModel("First", "First question", "First answer"),
        CardModel("Second", "Second question", "Second answer"),
        CardModel("Third", "Third question", "Third answer"),
        CardModel("Fourth", "Fourth question", "Fourth answer"),
            CardModel("Fifth", "Fifth question", "Fifth answer"),
            CardModel("Sixth", "Sixth question", "Sixth answer"),
            CardModel("Seventh", "Seventh question", "Seventh answer")
    )

    fun addCard(card: CardModel) {
        cards.add(card)
    }

    fun editCard(card: CardModel, position: Int) {
        cards[position] = card
    }

    fun getCard(position: Int): CardModel {
        return cards[position]
    }

    fun getCardList(): MutableList<CardModel> {
        return cards
    }

    fun deleteCard(position: Int) {
        cards.removeAt(position)
    }

}
