package ru.anikey.mymindcards.views

import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface MainView : MvpView {
    fun showList(list: List<CardModel>)
    fun addCard()
    fun startTest()
}