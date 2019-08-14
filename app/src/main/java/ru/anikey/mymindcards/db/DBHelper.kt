package ru.anikey.mymindcards.db

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.anikey.mymindcards.app.App.Companion.applicationContext

private const val DB_NAME = "cards.db"
private const val DB_VERSION = 1

object DBHelper : SQLiteOpenHelper(applicationContext(), DB_NAME, null, DB_VERSION) {

    const val TABLE_CARDS = "cards"

    const val COLUMN_ID = "id"
    const val COLUMN_TITLE = "title"
    const val COLUMN_QUESTION = "question"
    const val COLUMN_ANSWER = "answer"

    private const val CREATE_TABLE_QUERY =
        "create table $TABLE_CARDS ($COLUMN_ID integer primary key autoincrement, $COLUMN_TITLE text, $COLUMN_QUESTION text, $COLUMN_ANSWER text);"

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