package screen

import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import base.ATElements.mParent
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
import ru.anikey.mymindcards.R

object ATTestingScreen {


    // Заголовок формы тестирования
    private val mFormTitle = onView(allOf(
        instanceOf(TextView::class.java),
        mParent(id = R.id.test_toolbar)
    ))

    // Кнопка возврата
    private val mBackButton = onView(allOf(
        instanceOf(ImageButton::class.java),
        mParent(id = R.id.test_toolbar)
    ))

    // Кнопка показать ответ карточки
    private val mShowAnswerButton = onView(allOf(
        instanceOf(Button::class.java),
        withId(R.id.test_show_answer_button)
    ))

    // Кнопка подтверждающая ответ
    private val mCorrectlyButton = onView(allOf(
        instanceOf(Button::class.java),
        withId(R.id.test_is_true_button)
    ))

    // Кнопка не подтверждающая ответ
    private val mIncorrectlyButton = onView(allOf(
        instanceOf(Button::class.java),
        withId(R.id.test_is_false_button)
    ))

    // Заголовок вопроса карточки
    private val mCardTitle = onView(allOf(
        instanceOf(TextView::class.java),
        withId(R.id.test_title_text)
    ))

    // Текст вопроса карточки
    private val mCardText = onView(allOf(
        instanceOf(TextView::class.java),
        withId(R.id.test_question_text)
    ))

    // Заголовок алерта результата тестирования
    private val mAlertResultName = onView(allOf(
        withText("Ваш результат")
    ))

    // Кнопка алерта результата тестирования
    private val mAlertResultButton = onView(
        Matchers.allOf(
            Matchers.instanceOf(Button::class.java),
            withId(android.R.id.button2)
        )
    )

    // Текст алерта результата тестирования
    private val mAlertResultText = onView(
        withId(android.R.id.message)
    )

    /** Проверка формы тестирования с вопросом */
    fun viewTestingFormWithQuest(head: String, quest: String) {
        mFormTitle
            .check(matches(isDisplayed()))
            .check(matches(withText("Тестирование")))
        mShowAnswerButton
            .check(matches(isDisplayed()))
            .check(matches(withText("ПОКАЗАТЬ ОТВЕТ")))
        mBackButton
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
        mCardTitle
            .check(matches(isDisplayed()))
            .check(matches(withText(head)))
        mCardText
            .check(matches(isDisplayed()))
            .check(matches(withText(quest)))
    }

    /** Проверка формы тестирования с ответом */
    fun viewTestingFormWithAnswer(head: String) {
        mShowAnswerButton.perform(click())
        mFormTitle
            .check(matches(isDisplayed()))
            .check(matches(withText("Тестирование")))
        mCorrectlyButton
            .check(matches(isDisplayed()))
            .check(matches(withText("ВЕРНО")))
        mIncorrectlyButton
            .check(matches(isDisplayed()))
            .check(matches(withText("НЕ ВЕРНО")))
        mBackButton
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
        mCardTitle
            .check(matches(isDisplayed()))
            .check(matches(withText(head)))
        mCardText.check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    /** Проверка формы результата тестирования */
    fun viewResultAlertForm() {
        mShowAnswerButton.perform(click())
        mCorrectlyButton.perform(click())
        mAlertResultName.check(matches(isDisplayed()))
        mAlertResultText
            .check(matches(isDisplayed()))
            .check(matches(withText(containsString("Правильных ответов: 1"))))
            .check(matches(withText(containsString("Неправильных ответов: 0"))))
            .check(matches(withText(containsString("Тест пройден на 100%"))))
        mAlertResultButton
            .check(matches(isDisplayed()))
            .check(matches(withText("OK")))
    }

    /** Проверка возврата на предыдущую форму */
    fun checkBackForm() {
        mBackButton.perform(click())
        mFormTitle.check(doesNotExist())
    }

}
