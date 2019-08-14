package ru.anikey.mymindcards.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.adapters.CardListAdapter
import ru.anikey.mymindcards.custom.CustomRecyclerView
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.MainPresenter
import ru.anikey.mymindcards.utils.ARG_CARD
import ru.anikey.mymindcards.utils.ARG_POSITION
import ru.anikey.mymindcards.utils.CODE_ADD_CARD_ACTIVITY
import ru.anikey.mymindcards.views.MainView

class MainActivity : MvpAppCompatActivity(), MainView, View.OnClickListener {
    private lateinit var mRecyclerView: CustomRecyclerView
    private lateinit var mAddCardButton: Button
    private lateinit var mStartTestButton: Button
    private lateinit var mEmptyView: TextView

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        mPresenter.initCardList()
    }

    /**
     * =================================================================================================================
     * Callbacks
     * =================================================================================================================
     */

    override fun showList(list: List<CardModel>) {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = CardListAdapter(list, this)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        mRecyclerView.setEmptyView(mEmptyView)
    }


    override fun startAddCardActivity() {
        val intent = Intent(this, AddCardActivity::class.java)
        startActivityForResult(intent, CODE_ADD_CARD_ACTIVITY)
    }

    override fun startEditCardActivity(card: CardModel, position: Int) {
        val intent = Intent(this, AddCardActivity::class.java)
        intent.putExtra(ARG_CARD, card)
        intent.putExtra(ARG_POSITION, position)
        startActivityForResult(intent, CODE_ADD_CARD_ACTIVITY)
    }

    override fun startTest() {
        val intent = Intent(this, TestActivity::class.java)
        startActivity(intent)
    }

    override fun showPopup(itemView: View, position: Int, card: CardModel) {
        val popupMenu = PopupMenu(itemView.context, itemView)
        popupMenu.inflate(R.menu.main_popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.main_popup_edit -> {
                    mPresenter.getClickedCard(position)
                    true
                }
                R.id.main_popup_delete -> {
                    mPresenter.deleteCard(card)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun showError(text: String) {
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }

    override fun showError(textResource: Int) {
        Toast.makeText(applicationContext, getString(textResource), Toast.LENGTH_LONG).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CODE_ADD_CARD_ACTIVITY && resultCode == Activity.RESULT_OK) {
            mPresenter.initCardList()
        }
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.main_add_card_btn -> mPresenter.addButtonPressed()
            R.id.main_start_test_btn -> mPresenter.startTestPressed()
        }
    }

    /**
     * =================================================================================================================
     * Support
     * =================================================================================================================
     */

    private fun initViews() {
        mRecyclerView = main_card_list
        mAddCardButton = main_add_card_btn
        mStartTestButton = main_start_test_btn
        mEmptyView = main_empty_text

        mAddCardButton.setOnClickListener(this)
        mStartTestButton.setOnClickListener(this)
    }

}
