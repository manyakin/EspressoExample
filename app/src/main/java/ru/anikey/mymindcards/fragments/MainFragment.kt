package ru.anikey.mymindcards.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_main.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.adapters.CardListAdapter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.MainPresenter
import ru.anikey.mymindcards.views.MainView

class MainFragment : MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        mPresenter.initCardList()
        return view
    }

    // MainView callbacks
    override fun showList(list: List<CardModel>) {
        main_card_list.setHasFixedSize(true)
        main_card_list.adapter = CardListAdapter(list)
        main_card_list.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    override fun addCard() {
    }

    override fun startTest() {
    }

}
