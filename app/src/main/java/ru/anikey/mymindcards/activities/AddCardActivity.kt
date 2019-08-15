package ru.anikey.mymindcards.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_add_card.*
import kotlinx.android.synthetic.main.custom_edit_text.view.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.custom.CustomTextInputLayout
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.AddCardPresenter
import ru.anikey.mymindcards.utils.ARG_CARD
import ru.anikey.mymindcards.utils.ARG_POSITION
import ru.anikey.mymindcards.utils.RESULT_ADD
import ru.anikey.mymindcards.utils.RESULT_EDIT
import ru.anikey.mymindcards.views.AddCardView

class AddCardActivity : MvpAppCompatActivity(), AddCardView, View.OnClickListener {
    private lateinit var mToolbar: Toolbar
    private lateinit var mTitle: CustomTextInputLayout
    private lateinit var mQuestion: CustomTextInputLayout
    private lateinit var mAnswer: CustomTextInputLayout
    private lateinit var mSave: Button

    private enum class Mode {
        ADD, EDIT
    }

    private lateinit var mode: Mode
    private var mCard: CardModel? = null
    private var mPosition: Int? = null

    @InjectPresenter
    lateinit var mPresenter: AddCardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        initExtras()
        initViews()
    }

    /**
     * =============================================================================================
     * Callbacks
     * =============================================================================================
     */

    override fun onClick(view: View) {
        when (view.id) {
            R.id.save_card_btn -> {
                checkFields()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun cardSaved(card: CardModel) {
        val data: Intent
        when (mode) {
            Mode.EDIT -> {
                data = Intent()
                data.putExtra(ARG_CARD, card)
                data.putExtra(ARG_POSITION, mPosition)
                setResult(RESULT_EDIT, data)
                finish()
            }
            Mode.ADD -> {
                data = Intent()
                data.putExtra(ARG_CARD, card)
                setResult(RESULT_ADD, data)
                finish()
            }
        }
    }

    /**
     * =============================================================================================
     * Support
     * =============================================================================================
     */

    private fun checkFields() {
        mTitle.validate()
        mQuestion.validate()
        mAnswer.validate()
        if (!mTitle.isEmpty && !mQuestion.isEmpty && !mAnswer.isEmpty) {

            val title = mTitle.edit_text.text.toString()
            val question = mQuestion.edit_text.text.toString()
            val answer = mAnswer.edit_text.text.toString()

            when (mode) {
                Mode.ADD -> mPresenter.saveCard(title, question, answer)
                Mode.EDIT -> mPresenter.editCard(mCard!!, title, question, answer)
            }
        }
    }

    private fun initViews() {
        mToolbar = add_card_toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.run {
            setDisplayHomeAsUpEnabled(true)
            title = when (mode) {
                Mode.ADD -> getString(R.string.add_card_toolbar_title)
                Mode.EDIT -> getString(R.string.edit_card_toolbar_title)
            }
        }

        mTitle = title_fld
        mQuestion = question_fld
        mAnswer = answer_fld

        mCard?.let { card ->
            mTitle.setText(card.title)
            mQuestion.setText(card.question)
            mAnswer.setText(card.answer)
        }

        mSave = save_card_btn
        mSave.setOnClickListener(this)
    }

    private fun initExtras() {
        if (intent.extras != null) {
            mode = Mode.EDIT
            mCard = intent.extras!!.getParcelable<CardModel?>(ARG_CARD)
            mPosition = intent.extras!!.getInt(ARG_POSITION)
        } else {
            mode = Mode.ADD
        }
    }
}
