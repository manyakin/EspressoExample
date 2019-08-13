package ru.anikey.mymindcards.repositories

import android.content.Context
import ru.anikey.mymindcards.db.DBHelper
import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.models.CardModel

object MainRepository {
    private lateinit var dbWriter: DBWriter
    private lateinit var dbReader: DBReader
    lateinit var cards: MutableList<CardModel>


    fun addCard(context: Context, title: String, question: String, answer: String) {
        dbWriter = DBWriter(context)
        dbWriter.addCard(title, question, answer)
    }

    fun editCard(context: Context, card: CardModel, title: String, question: String, answer: String) {
        dbWriter = DBWriter(context)
        dbWriter.editCard(card, title, question, answer)
    }

    fun getCard(context: Context, position: Int): CardModel {
        dbReader = DBReader(DBHelper.getInstance(context).readableDatabase)
        return dbReader.getPosition(position)
    }

    fun getCardList(context: Context): MutableList<CardModel> {
        dbReader = DBReader(DBHelper.getInstance(context).readableDatabase)
        cards = dbReader.open()
        return cards
    }

    fun deleteCard(context: Context, card: CardModel) {
        dbWriter = DBWriter(context)
        dbWriter.deleteCard(card)
    }

}
