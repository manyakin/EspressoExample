package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import ru.anikey.mymindcards.fragments.AddCardFragment
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository
import ru.anikey.mymindcards.views.MainView

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val mRepository = MainRepository(this)

    fun initCardList() {
        mRepository.loadCards()
    }

    fun buildList(list: List<CardModel>) {
        viewState.showList(list)
    }

    fun addButtonPressed() {
        val fragment = AddCardFragment(mRepository)
        viewState.showDialog(fragment)
    }

}
