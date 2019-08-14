package ru.anikey.mymindcards.db

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import ru.anikey.mymindcards.models.CardModel

object DBReader {
    private val dataBase: SQLiteDatabase = DBHelper.readableDatabase
    private lateinit var cursor: Cursor
    private val cardsColumns = arrayOf(
        DBHelper.COLUMN_ID,
        DBHelper.COLUMN_TITLE,
        DBHelper.COLUMN_QUESTION,
        DBHelper.COLUMN_ANSWER
    )

    fun open(): MutableList<CardModel> {
        return query()
    }

    fun refresh() {
        val position = cursor.position
        query()
        cursor.moveToPosition(position)
    }

    fun getPosition(position: Int): CardModel {
        cursor = dataBase.query(
            DBHelper.TABLE_CARDS,
            cardsColumns,
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToPosition(position)
        return cursorToCard()
    }

    fun getCount(): Int {
        return cursor.count
    }


    private fun cursorToCard(): CardModel {
        val card = CardModel(
            cursor.getLong(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        cursor.close()
        return card
    }

    /**
     * ===============================================================================
     * Support
     * ===============================================================================
     */

    private fun query(): MutableList<CardModel> {
        val cards = mutableListOf<CardModel>()
        cursor = dataBase.query(
            DBHelper.TABLE_CARDS,
            cardsColumns,
            null,
            null,
            null,
            null,
            null
        )
        try {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getLong(0)
                    val title = cursor.getString(1)
                    val question = cursor.getString(2)
                    val answer = cursor.getString(3)
                    val card = CardModel(
                        id,
                        title,
                        question,
                        answer
                    )
                    cards.add(card)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
        }
        return cards
    }
}