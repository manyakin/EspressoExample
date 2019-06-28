package ru.anikey.mymindcards.presentation.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.anikey.mymindcards.R

class MainFragment : Fragment(), MainContract.MainView {

    private val mPresenter = MainPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        initViews(view)
        return view
    }

    /**
     * =================================================================================================================
     * Support
     * =================================================================================================================
     */

    private fun initViews(view: View) {
        mPresenter.initCardList(view)
    }

    // todo: реализовать кнопки
}
