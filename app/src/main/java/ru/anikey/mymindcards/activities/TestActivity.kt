package ru.anikey.mymindcards.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_test.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.presenters.TestPresenter
import ru.anikey.mymindcards.views.TestView

class TestActivity : MvpAppCompatActivity(), TestView, View.OnClickListener {
    private lateinit var mToolbar: Toolbar
    private lateinit var mTitle: TextView
    private lateinit var mQuestion: TextView
    private lateinit var mAnswer: TextView
    private lateinit var mShowAnswerBtn: Button
    private lateinit var mIsCorrectLayout: LinearLayout
    private lateinit var mTrueBtn: Button
    private lateinit var mFalseBtn: Button

    @InjectPresenter
    lateinit var mPresenter: TestPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        initViews()

        mPresenter.getCard()
    }

    /**
     * =============================================================================================
     * Callbacks
     * =============================================================================================
     */

    override fun showCard(card: CardModel) {
        mTitle.text = card.title
        mQuestion.text = card.question
        mAnswer.text = card.answer

        mQuestion.visibility = View.VISIBLE
        mShowAnswerBtn.visibility = View.VISIBLE

        mAnswer.visibility = View.GONE
        mIsCorrectLayout.visibility = View.GONE
    }

    override fun showAnswer() {
        mQuestion.visibility = View.GONE
        mShowAnswerBtn.visibility = View.GONE

        mAnswer.visibility = View.VISIBLE
        mIsCorrectLayout.visibility = View.VISIBLE
    }

    override fun showResult(trueAnswers: String, falseAnswers: String, result: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.test_result_title))
                .setMessage(getString(R.string.test_result_info, trueAnswers, falseAnswers, result))
                .setCancelable(false)
                .setNegativeButton("OK") { dialog, _ ->
                    dialog.cancel()
                    finish()
                }
        val alert = builder.create()
        alert.show()
    }

    override fun onClick(viewItem: View) {
        when (viewItem.id) {
            R.id.test_show_answer_button -> {
                mPresenter.setCount()
            }
            R.id.test_is_true_button -> {
                mPresenter.answerIsTrue()
            }
            R.id.test_is_false_button -> {
                mPresenter.answerIsFalse()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * =============================================================================================
     * Support
     * =============================================================================================
     */

    private fun initViews() {
        mToolbar = test_toolbar
        setSupportActionBar(mToolbar)
        supportActionBar!!.run {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.test_toolbar_title)
        }

        mTitle = test_title_text
        mQuestion = test_question_text
        mAnswer = test_answer_text
        mShowAnswerBtn = test_show_answer_button
        mIsCorrectLayout = test_is_answer_correct_layout
        mTrueBtn = test_is_true_button
        mFalseBtn = test_is_false_button

        mShowAnswerBtn.setOnClickListener(this)
        mTrueBtn.setOnClickListener(this)
        mFalseBtn.setOnClickListener(this)
    }

}
