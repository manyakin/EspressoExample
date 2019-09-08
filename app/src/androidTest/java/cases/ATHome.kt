package cases

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import base.ATBase
import base.Data
import org.junit.Test
import org.junit.runner.RunWith
import screen.ATCardScreen
import screen.ATHomeScreen


@RunWith(AndroidJUnit4ClassRunner::class)
class ATHome : ATBase() {

    /** Отображение главной формы без карточек */
    @Test
    fun checkViewEmptyCardForm() {
        ATHomeScreen.viewEmptyCardForm()
    }

    /** Проверка работы поиска по заголовку карточки */
    @Test
    fun checkWorkSearchByTitle() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen
            .openSearchForm()
            .fillSearch(Data.HEAD.value)
            .viewSmallCardForm(Data.HEAD.value, Data.QUEST.value)
    }

    /** Проверка работы поиска по вопросу карточки */
    @Test
    fun checkWorkSearchByQuestion() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen
            .openSearchForm()
            .fillSearch(Data.QUEST.value)
            .viewSmallCardForm(Data.HEAD.value, Data.QUEST.value)
    }

    /** Проверка работы сброса поиска */
    @Test
    fun checkResetSearch() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen
            .openSearchForm()
            .fillSearch(Data.QUEST.value)
            .clearSearchForm()
            .viewSmallCardForm(Data.HEAD.value, Data.QUEST.value)
    }

    /** Отображение формы маленькой карточки */
    @Test
    fun checkSmallCardForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.viewSmallCardForm(Data.HEAD.value, Data.QUEST.value)
    }

    /** Проверка отображения контекстного меню карточки */
    @Test
    fun checkViewCardContextMenu() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.viewCardContextMenu()
    }

    /** Проверка удаления карточки */
    @Test
    fun checkDeleteCard() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.deleteCard()
    }

    /** Проверка формы ответа карточки */
    @Test
    fun checkViewAnswerCardAlertForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.viewAnswerAlertForm(Data.ANSWER.value)
    }

}
