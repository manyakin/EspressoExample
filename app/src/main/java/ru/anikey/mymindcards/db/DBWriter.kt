package ru.anikey.mymindcards.db

import android.content.ContentValues
import ru.anikey.mymindcards.models.CardModel

object DBWriter {
    private val dataBase = DBHelper.writableDatabase

    fun addCard(title: String, question: String, answer: String): CardModel {
        val values = ContentValues()
        values.put(DBHelper.COLUMN_TITLE, title)
        values.put(DBHelper.COLUMN_QUESTION, question)
        values.put(DBHelper.COLUMN_ANSWER, answer)
        val insertId = dataBase.insert(DBHelper.TABLE_CARDS, null, values)

        return CardModel(insertId, title, question, answer)
    }

    fun editCard(oldCard: CardModel, title: String, question: String, answer: String): CardModel {
        val editedCard = ContentValues()
        editedCard.put(DBHelper.COLUMN_ID, oldCard.id)
        editedCard.put(DBHelper.COLUMN_TITLE, title)
        editedCard.put(DBHelper.COLUMN_QUESTION, question)
        editedCard.put(DBHelper.COLUMN_ANSWER, answer)

        dataBase.update(
            DBHelper.TABLE_CARDS,
            editedCard,
            DBHelper.COLUMN_ID + "=" + oldCard.id,
            null
        )
        return CardModel(oldCard.id, title, question, answer)
    }

    fun deleteCard(card: CardModel) {
        val id = card.id
        dataBase.delete(DBHelper.TABLE_CARDS, DBHelper.COLUMN_ID + "=" + id, null)
    }

}
