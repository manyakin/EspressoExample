package ru.anikey.mymindcards.views

import com.arellomobile.mvp.MvpAppCompatDialogFragment
import com.arellomobile.mvp.MvpView
import ru.anikey.mymindcards.models.CardModel

interface MainView : MvpView {
    fun showList(list: List<CardModel>)
    fun showDialog(fragment: MvpAppCompatDialogFragment)
    fun startTest()
}