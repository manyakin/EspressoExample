package ru.anikey.mymindcards.activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
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
import ru.anikey.mymindcards.views.AddCardView

class AddCardActivity : MvpAppCompatActivity(), AddCardView, View.OnClickListener {
    private lateinit var mTitle: CustomTextInputLayout
    private lateinit var mQestion: CustomTextInputLayout
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

    override fun cardSaved() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    /**
     * =============================================================================================
     * Support
     * =============================================================================================
     */

    private fun checkFields() {
        mTitle.validate()
        mQestion.validate()
        mAnswer.validate()
        if (!mTitle.isEmpty && !mQestion.isEmpty && !mAnswer.isEmpty) {

            val title = mTitle.edit_text.text.toString()
            val question = mQestion.edit_text.text.toString()
            val answer = mAnswer.edit_text.text.toString()

            when (mode) {
                Mode.ADD -> mPresenter.saveCard(applicationContext, title, question, answer)
                Mode.EDIT -> mPresenter.editCard(applicationContext, mCard!!, title, question, answer)
            }
        }
    }

    private fun initViews() {
        mTitle = title_fld
        mQestion = question_fld
        mAnswer = answer_fld

        mCard?.let { card ->
            mTitle.setText(card.title)
            mQestion.setText(card.question)
            mAnswer.setText(card.answer)
        }

        title = when (mode) {
            Mode.ADD -> getString(R.string.add_card_toolbar_title)
            Mode.EDIT -> getString(R.string.edit_card_toolbar_title)
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
