package ru.anikey.mymindcards.repositories

import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.models.CardModel

class MainRepository(var dbWriter: DBWriter, var dbReader: DBReader) {
    var cards = mutableListOf<CardModel>()

    init {
        cards = dbReader.open()
    }

    fun addCard(title: String, question: String, answer: String): CardModel {
        return dbWriter.addCard(title, question, answer)
    }

    fun editCard(card: CardModel, title: String, question: String, answer: String): CardModel {
        return dbWriter.editCard(card, title, question, answer)
    }

    fun getCard(position: Int): CardModel {
        return dbReader.getPosition(position)
    }

    fun getCardList(): MutableList<CardModel> {
        cards.clear()
        cards.addAll(dbReader.open())
        return cards
    }

    fun deleteCard(card: CardModel) {
        dbWriter.deleteCard(card)
    }

}
