package ru.anikey.mymindcards.repositories

import io.reactivex.Observable
import ru.anikey.mymindcards.db.DBReader
import ru.anikey.mymindcards.db.DBWriter
import ru.anikey.mymindcards.models.CardModel

class MainRepository(var dbWriter: DBWriter, var dbReader: DBReader) : IMainRepository {

    var cards = mutableListOf<CardModel>()

    init {
        cards = dbReader.open()
    }

    override fun getCardList(): Observable<MutableList<CardModel>> {
        return Observable.just(dbReader.open())
    }

    override fun addCard(title: String, question: String, answer: String): Observable<CardModel> {
        return Observable.just(dbWriter.addCard(title, question, answer))
    }

    override fun editCard(
        card: CardModel,
        title: String,
        question: String,
        answer: String
    ): Observable<CardModel> {
        return Observable.just(dbWriter.editCard(card, title, question, answer))
    }

    override fun getCard(position: Int): Observable<CardModel> {
        return Observable.just(dbReader.getPosition(position))
    }

    override fun deleteCard(card: CardModel) {
        dbWriter.deleteCard(card)
    }

}
