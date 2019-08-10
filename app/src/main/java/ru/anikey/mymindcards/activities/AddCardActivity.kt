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
import ru.anikey.mymindcards.views.AddCardView

class AddCardActivity : MvpAppCompatActivity(), AddCardView, View.OnClickListener {
    private lateinit var mTitle: CustomTextInputLayout
    private lateinit var mQestion: CustomTextInputLayout
    private lateinit var mAnswer: CustomTextInputLayout
    private lateinit var mSave: Button

    @InjectPresenter
    lateinit var mPresenter: AddCardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
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
            val card = CardModel(
                    title = mTitle.edit_text.text.toString(),
                    question = mQestion.edit_text.text.toString(),
                    answer = mAnswer.edit_text.text.toString()
            )
            mPresenter.saveCard(card)
        }
    }

    private fun initViews() {
        mTitle = title_fld
        mQestion = question_fld
        mAnswer = answer_fld

        mSave = save_card_btn
        mSave.setOnClickListener(this)
    }
}
