package ru.anikey.mymindcards.custom

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.custom_edit_text.view.*
import ru.anikey.mymindcards.R

class CustomTextInputLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), TextWatcher {

    private val rootLayout: TextInputLayout = LayoutInflater.from(context).inflate(
        R.layout.custom_edit_text, this, false
    ) as TextInputLayout

    private val editText: TextInputEditText

    private var placeholder: String? = null
    private var inputError: String? = null

    private var isValidate = false
    var isEmpty = true

    init {
        editText = rootLayout.edit_text
        editText.addTextChangedListener(this)
        initAttrs(attrs)
    }

    fun validate() {
        isValidate = true
        runValidation()
    }

    /**
     * =============================================================================================
     * Callbacks
     * =============================================================================================
     */

    override fun afterTextChanged(p0: Editable?) {
        runValidation()
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        isEmpty = text.isNullOrEmpty()
    }

    /**
     * =============================================================================================
     * Support
     * =============================================================================================
     */

    private fun runValidation() {
        if (isValidate and isEmpty) {
            rootLayout.isErrorEnabled = true
            rootLayout.error = inputError
        } else {
            rootLayout.isErrorEnabled = false
        }
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CustomTextInputLayout)

            placeholder = typedArray.getString(R.styleable.CustomTextInputLayout_placeholder)
            inputError = typedArray.getString(R.styleable.CustomTextInputLayout_input_error)

            rootLayout.hint = placeholder

            typedArray.recycle()

            addView(rootLayout)
        }
    }

}