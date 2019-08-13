package ru.anikey.mymindcards.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

private const val DB_NAME = "cards.db"
private const val DB_VERSION = 1


class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        const val TABLE_CARDS = "cards"

        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_QUESTION = "question"
        const val COLUMN_ANSWER = "answer"

        private const val CREATE_TABLE_QUERY =
            "create table $TABLE_CARDS ($COLUMN_ID integer primary key autoincrement, $COLUMN_TITLE text, $COLUMN_QUESTION text, $COLUMN_ANSWER text);"

        private var instance: DBHelper? = null

        fun getInstance(context: Context): DBHelper {
            if (instance == null) {
                instance = DBHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion == 1 && newVersion == 2) {
            val updateQuery = "drop table if exists $TABLE_CARDS"
            db.execSQL(updateQuery)
            db.execSQL(CREATE_TABLE_QUERY)
        }
    }

}