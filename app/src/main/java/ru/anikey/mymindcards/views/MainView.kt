package ru.anikey.mymindcards.views

import android.view.View
import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface MainView : MvpView {
    fun showList(list: List<CardModel>)
    fun showPopup(itemView: View, position: Int)
    fun startAddCardActivity()
    fun startEditCardActivity(card: CardModel, position: Int)
    fun startTest()
}