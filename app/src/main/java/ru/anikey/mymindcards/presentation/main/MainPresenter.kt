package ru.anikey.mymindcards.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import ru.anikey.mymindcards.adapters.CardListAdapter

class MainPresenter : MainContract.MainPresenter {

    override fun initCardList(view: View) {
        val cardList = view.main_card_list
        cardList.setHasFixedSize(true)
        cardList.adapter = CardListAdapter()
        cardList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

}
