package screen

import android.widget.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import base.ATElements.mParent
import org.hamcrest.Matchers.*
import ru.anikey.mymindcards.R

object ATHomeScreen {

    // Заголовок формы карточек
    private val mMemoCardForm = onView(allOf(
        instanceOf(TextView::class.java),
        mParent(id = R.id.main_toolbar)
    ))

    // Кнопка поиска
    private val mSearchButton = onView(allOf(
        instanceOf(SearchView::class.java),
        withId(R.id.main_toolbar_search)
    ))

    // Поле поиска
    private val mSearchField = onView(allOf(
        instanceOf(AutoCompleteTextView::class.java)
    ))

    // Кнопка сброса формы поиска
    private val mSearchClearButton = onView(
        withContentDescription("Clear query")
    )

    // Подсказка пустой формы с карточками
    private val mEmptyMemoCardHint = onView(allOf(
        instanceOf(TextView::class.java),
        withId(R.id.main_empty_text)
    ))

    // Кнопка добавления новой карточки
    private val mAddCard = onView(allOf(
        instanceOf(Button::class.java),
        withId(R.id.main_add_card_button)
    ))

    // Кнопка начала тестирования
    private val mPlay = onView(allOf(
        instanceOf(ImageView::class.java),
        withId(R.id.main_fab)
    ))

    // Заголовок маленькой карточки
    private val mCardTitle = onView(allOf(
        instanceOf(TextView::class.java),
        withId(R.id.card_title)
    ))

    // Текст маленькой карточки
    private val mCardText = onView(allOf(
        instanceOf(TextView::class.java),
        withId(R.id.card_text)
    ))

    // Кнопка редактирования в контекстном меню
    private val mContextEditButton = onView(allOf(
        instanceOf(TextView::class.java),
        withText("Редактировать")
    ))

    // Кнопка удаления в контекстном меню
    private val mContextDeleteButton = onView(allOf(
        instanceOf(TextView::class.java),
        withText("Удалить")
    ))

    // Заголовок алерта ответа карточки
    private val mAlertAnswerTitle = onView(allOf(
       withText("Ответ:")
    ))

    // Текст алерта ответа карточки
    private val mAlertAnswerText = onView(allOf(
        withId(android.R.id.message)
    ))

    // Кнопка алерта ответа карточки
    private val mAlertAnswerButton = onView(allOf(
        instanceOf(Button::class.java),
        withId(android.R.id.button2)
    ))

    /** Проверка отображения главной формы без карточек */
    fun viewEmptyCardForm() {
        mMemoCardForm
            .check(matches(isDisplayed()))
            .check(matches(withText("Карточки запоминалки")))
        mEmptyMemoCardHint
            .check(matches(isDisplayed()))
            .check(matches(withText("Список карточек пуст")))
        mAddCard
            .check(matches(isDisplayed()))
            .check(matches(withText("ДОБАВИТЬ КАРТОЧКУ")))
        mSearchButton
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
        mPlay
            .check(matches(isDisplayed()))
            .check(matches(isEnabled()))
    }

    /** Отображение формы алерта ответа карточки */
    fun viewAnswerAlertForm(answer: String) {
        mCardTitle.perform(click())
        mAlertAnswerTitle.check(matches(isDisplayed()))
        mAlertAnswerText
            .check(matches(isDisplayed()))
            .check(matches(withText(answer)))
        mAlertAnswerButton
            .check(matches(isDisplayed()))
            .check(matches(withText("OK")))
    }

    /** Отображение формы маленькой карточки */
    fun viewSmallCardForm(name: String, text: String) : ATHomeScreen {
        mCardTitle
            .check(matches(isDisplayed()))
            .check(matches(withText(name)))
        mCardText
            .check(matches(isDisplayed()))
            .check(matches(withText(text)))
        return this
    }

    /** Отображение контекстного меню карточки */
    fun viewCardContextMenu() {
        mCardTitle.perform(longClick())
        mContextEditButton.check(matches(isDisplayed()))
        mContextDeleteButton.check(matches(isDisplayed()))
    }

    /** Удаление карточки */
    fun deleteCard() {
        mCardTitle.perform(longClick())
        mContextDeleteButton.perform(click())
        mEmptyMemoCardHint.check(matches(withText("Список карточек пуст")))
    }

    /** Проверка результата сброса поиска */
    fun clearSearchForm() : ATHomeScreen {
        mSearchClearButton.perform(click())
        mSearchField.check(matches(withText(isEmptyString())))
        mSearchClearButton.perform(click())
        return this
    }

    /** Переход на форму добавления карточки */
    fun openAddCardForm() {
        mAddCard.perform(click())
    }

    /** Переход на форму тестирования карточек */
    fun openTestingForm() {
        mPlay.perform(click())
    }

    /** Переход на форму редактирования карточки */
    fun openEditCardForm() {
        mCardTitle.perform(longClick())
        mContextEditButton.perform(click())
    }

    /** Открытие формы поиска */
    fun openSearchForm() : ATHomeScreen {
        mSearchButton.perform(click())
        return this
    }

    /** Заполнение поля поиска */
    fun fillSearch(name: String) : ATHomeScreen {
        mSearchField.perform(replaceText(name))
        return this
    }

}
