package ru.anikey.mymindcards.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.adapters.CardListAdapter
import ru.anikey.mymindcards.custom.CustomRecyclerView
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.MainPresenter
import ru.anikey.mymindcards.utils.*
import ru.anikey.mymindcards.views.MainView

class MainActivity : MvpAppCompatActivity(), MainView, View.OnClickListener {
    private lateinit var mToolbar: Toolbar
    private lateinit var mSearchView: SearchView
    private lateinit var mFab: FloatingActionButton
    private lateinit var mAddButton: Button
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: CustomRecyclerView
    private lateinit var mAdapter: CardListAdapter
    private lateinit var mEmptyView: TextView

    @InjectPresenter
    lateinit var mPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initAdapter()

        mPresenter.initCardList()
    }

    override fun onDestroy() {
        mPresenter.dispose()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
        mSearchView = menu.findItem(R.id.main_toolbar_search).actionView as SearchView
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                mAdapter.filter(query!!)
                return true
            }
        })
        return true
    }

    override fun onBackPressed() {
        if (!mSearchView.isIconified) {
            mSearchView.isIconified = true
        } else {
            super.onBackPressed()
        }
    }

    /**
     * =============================================================================================
     * Callbacks
     * =============================================================================================
     */

    override fun showList(list: List<CardModel>) {
        val controller = AnimationUtils.loadLayoutAnimation(
            applicationContext,
            R.anim.layout_animation_fall_down
        )
        mRecyclerView.layoutAnimation = controller
        mAdapter.setData(list)
        mRecyclerView.scheduleLayoutAnimation()
        mProgressBar.visibility = View.GONE
        mRecyclerView.setEmptyView(mEmptyView)
    }

    override fun showAnswer(card: CardModel) {
        val builder = AlertDialog.Builder(this)
        val alert = builder.setTitle("Ответ:")
            .setMessage(card.answer)
            .setCancelable(false)
            .setNegativeButton("Ok") { dialog, _ ->
                dialog.cancel()
            }.create()
        alert.show()
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
                    mAdapter.deleteCard(position)
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
        if (requestCode == CODE_ADD_CARD_ACTIVITY) {
            when (resultCode) {
                RESULT_EDIT -> {
                    data?.let {
                        mAdapter.updateCard(
                            it.getParcelableExtra(ARG_CARD)!!,
                            it.getIntExtra(ARG_POSITION, 0)
                        )
                    }
                }
                RESULT_ADD -> {
                    data?.let {
                        mAdapter.insertCard(
                            it.getParcelableExtra(ARG_CARD)!!
                        )
                    }
                }
            }
        }
    }

    override fun onClick(itemView: View) {
        when (itemView.id) {
            R.id.main_fab -> mPresenter.startTestPressed()
            R.id.main_add_card_button -> mPresenter.addButtonPressed()
        }
    }

    /**
     * =============================================================================================
     * Support
     * =============================================================================================
     */

    private fun initViews() {
        mToolbar = main_toolbar
        setSupportActionBar(mToolbar)

        mFab = main_fab
        mAddButton = main_add_card_button
        mProgressBar = main_progress_bar
        mRecyclerView = main_card_list
        mEmptyView = main_empty_text

        mFab.setOnClickListener(this)
        mAddButton.setOnClickListener(this)
    }

    private fun initAdapter() {
        mAdapter = CardListAdapter(this)
        mAdapter.setHasStableIds(true)

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) mFab.hide()
                else mFab.show()
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}
