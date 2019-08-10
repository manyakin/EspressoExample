package ru.anikey.mymindcards.views

import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface MainView : MvpView {
    fun showList(list: List<CardModel>)
    fun startAddCardActivity()
    fun startEditCardActivity(card: CardModel, position: Int)
    fun startTest()
    fun onCardClicked(position: Int)
}