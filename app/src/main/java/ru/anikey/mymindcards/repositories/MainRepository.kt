package ru.anikey.mymindcards.repositories

import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.models.CardModel

object MainRepository {
    lateinit var cards: MutableList<CardModel>

    fun addCard(title: String, question: String, answer: String): CardModel {
        return DBWriter.addCard(title, question, answer)
    }

    fun editCard(card: CardModel, title: String, question: String, answer: String): CardModel {
        return DBWriter.editCard(card, title, question, answer)
    }

    fun getCard(position: Int): CardModel {
        return DBReader.getPosition(position)
    }

    fun getCardList(): MutableList<CardModel> {
        cards = DBReader.open()
        return cards
    }

    fun deleteCard(card: CardModel) {
        DBWriter.deleteCard(card)
    }

}
