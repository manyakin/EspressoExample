package ru.anikey.mymindcards.repositories

import io.reactivex.Observable
import ru.anikey.mymindcards.models.CardModel

interface IMainRepository {
    fun getCardList(): Observable<MutableList<CardModel>>

    fun addCard(title: String, question: String, answer: String): Observable<CardModel>

    fun editCard(
        card: CardModel,
        title: String,
        question: String,
        answer: String
    ): Observable<CardModel>

    fun getCard(position: Int): Observable<CardModel>

    fun deleteCard(card: CardModel)
}