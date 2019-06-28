package ru.anikey.mymindcards


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_main.view.*
import ru.anikey.mymindcards.adapters.CardListAdapter

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initRecycle(view)

        return view
    }

    /**
     * =================================================================================================================
     * Support
     * =================================================================================================================
     */

    private fun initRecycle(view: View) {
        val cardList = view.main_card_list
        cardList.setHasFixedSize(true)
        cardList.adapter = CardListAdapter()
        cardList.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
    }

    // todo: реализовать кнопки
}
