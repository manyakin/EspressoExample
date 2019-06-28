package ru.anikey.mymindcards.presentation.main

import android.view.View

interface MainContract {

    interface MainView

    interface MainPresenter {
        fun initCardList(view: View)
    }
}