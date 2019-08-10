package ru.anikey.mymindcards.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.arellomobile.mvp.MvpAppCompatDialogFragment
import kotlinx.android.synthetic.main.custom_edit_text.view.*
import kotlinx.android.synthetic.main.fragment_add_card.view.*
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.custom.CustomTextInputLayout
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.MainRepository

class AddCardFragment(private val repository: MainRepository) : MvpAppCompatDialogFragment(), View.OnClickListener {
    private lateinit var mTitle: CustomTextInputLayout
    private lateinit var mQestion: CustomTextInputLayout
    private lateinit var mAnswer: CustomTextInputLayout
    private lateinit var mSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_add_card, container, false)
        initViews(root)
        return root
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
            repository.addCard(card)
            dismiss()
        }
    }

    private fun initViews(root: View) {
        mTitle = root.title_fld
        mQestion = root.question_fld
        mAnswer = root.answer_fld

        mSave = root.save_card_btn
        mSave.setOnClickListener(this)
    }

}
