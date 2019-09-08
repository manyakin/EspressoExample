package screen

import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import base.ATElements.mAncestor
import base.ATElements.mParent
import org.hamcrest.Matchers.*
import ru.anikey.mymindcards.R


object ATCardScreen {

    // Заголовок формы добавления карточки
    private val mAddCardForm = onView(allOf(
        instanceOf(TextView::class.java),
        mParent(id = R.id.add_card_toolbar)
    ))

    // Кнопка назад
    private val mBackButton = onView(allOf(
        instanceOf(ImageButton::class.java),
        mParent(id = R.id.add_card_toolbar)
    ))

    // Поле заголовка карточки
    private val mHeadingField = onView(allOf(
        instanceOf(EditText::class.java),
        mAncestor(id = R.id.title_fld)
    ))

    // Ошибка валидации поля заголовка карточки
    private val mHeadingFieldError = onView(allOf(
        withId(R.id.textinput_error),
        mAncestor(id = R.id.title_fld)
    ))

    // Поле вопроса карточки
    private val mQuestionField = onView(allOf(
        instanceOf(EditText::class.java),
        mAncestor(id = R.id.question_fld)
    ))

    // Ошибка валидации поля вопроса карточки
    private val mQuestionFieldError = onView(allOf(
        withId(R.id.textinput_error),
        mAncestor(id = R.id.question_fld)
    ))

    // Поле ответа карточки
    private val mAnswerField = onView(allOf(
        instanceOf(EditText::class.java),
        mAncestor(id = R.id.answer_fld)
    ))

    // Ошибка валидации поля ответа карточки
    private val mAnswerFieldError = onView(allOf(
        withId(R.id.textinput_error),
        mAncestor(id = R.id.answer_fld)
    ))

    // Кнопка сохранения формы
    private val mSaveButton = onView(allOf(
        instanceOf(Button::class.java),
        withId(R.id.save_card_btn)
    ))

    /** Проверка отображения формы добавления карточки */
    fun viewAddCardForm() {
        mAddCardForm
            .check(matches(isDisplayed()))
            .check(matches(withText("Добавление карточки")))
        mHeadingField
            .check(matches(isDisplayed()))
            .check(matches(withHint("Заголовок")))
        mQuestionField
            .check(matches(isDisplayed()))
            .check(matches(withHint("Вопрос")))
        mAnswerField
            .check(matches(isDisplayed()))
            .check(matches(withHint("Ответ")))
        mSaveButton
            .check(matches(isDisplayed()))
            .check(matches(withText("Сохранить")))
        mBackButton
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
    }

    /** Проверка отображения формы редактирования карточки */
    fun viewEditCardForm(head: String, question: String, answer: String) {
        mAddCardForm
            .check(matches(isDisplayed()))
            .check(matches(withText("Редактирование карточки")))
        mBackButton
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
        mHeadingField
            .check(matches(isDisplayed()))
            .check(matches(withText(head)))
        mQuestionField
            .check(matches(isDisplayed()))
            .check(matches(withText(question)))
        mAnswerField
            .check(matches(isDisplayed()))
            .check(matches(withText(answer)))
        mSaveButton
            .check(matches(isDisplayed()))
            .check(matches(withText("Сохранить")))
    }

    /** Проверка отображения ошибок валидации */
    fun viewValidateError() {
        mSaveButton.perform(click())
        mHeadingFieldError
            .check(matches(isDisplayed()))
            .check(matches(withText("Введите заголовок")))
        mQuestionFieldError
            .check(matches(isDisplayed()))
            .check(matches(withText("Введите вопрос")))
        mAnswerFieldError
            .check(matches(isDisplayed()))
            .check(matches(withText("Введите ответ")))
    }

    /** Проверка отображения ошибок валидации */
    fun hideValidateError() {
        mSaveButton.perform(click())
        mHeadingField.perform(replaceText("Заголовок карточки"))
        mQuestionField.perform(replaceText("Вопрос карточки"))
        mAnswerField.perform(replaceText("Ответ карточки"))

        mHeadingFieldError.check(doesNotExist())
        mQuestionFieldError.check(doesNotExist())
        mAnswerFieldError.check(doesNotExist())
    }

    /** Добавление новой карточки */
    fun fillAddCard(head: String, question: String, answer: String) {
        mHeadingField
            .perform(clearText())
            .perform(replaceText(head))
        mQuestionField
            .perform(clearText())
            .perform(replaceText(question))
        mAnswerField
            .perform(clearText())
            .perform(replaceText(answer))
        mSaveButton.perform(click())
        Thread.sleep(500)
    }

    /** Проверка возврата с формы */
    fun checkBackForm() {
        mBackButton.perform(click())
        mAddCardForm.check(doesNotExist())
    }

    /** Очистка формы */
    fun clearForm() {
        mHeadingField.perform(clearText())
        mQuestionField.perform(clearText())
        mAnswerField .perform(clearText())
    }

}
