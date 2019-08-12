package ru.anikey.mymindcards.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.adapters.CardListAdapter
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.MainPresenter
import ru.anikey.mymindcards.utils.ARG_CARD
import ru.anikey.mymindcards.utils.ARG_POSITION
import ru.anikey.mymindcards.utils.CODE_ADD_CARD_ACTIVITY
import ru.anikey.mymindcards.views.MainView

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAddCardButton: Button
    private lateinit var mStartTestButton: Button

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        mPresenter.initCardList()

        mAddCardButton.setOnClickListener {
            mPresenter.addButtonPressed()
        }
    }

    /**
     * =================================================================================================================
     * Callbacks
     * =================================================================================================================
     */

    override fun showList(list: List<CardModel>) {
        main_card_list.setHasFixedSize(true)
        main_card_list.adapter = CardListAdapter(list, this)
        main_card_list.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
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
    }

    override fun showPopup(itemView: View, position: Int) {
        val popupMenu = PopupMenu(itemView.context, itemView)
        popupMenu.inflate(R.menu.main_popup_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.main_popup_edit -> {
                    mPresenter.getClickedCard(position)
                    true
                }
                R.id.main_popup_delete -> {
                    mPresenter.deleteCard(position)
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CODE_ADD_CARD_ACTIVITY && resultCode == Activity.RESULT_OK) {
            mPresenter.initCardList()
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
        mStartTestButton = main_start_training_btn
    }

}
