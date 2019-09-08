package cases

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import base.ATBase
import base.Data
import org.junit.Test
import org.junit.runner.RunWith
import screen.ATCardScreen
import screen.ATHomeScreen


@RunWith(AndroidJUnit4ClassRunner::class)
class ATCreateCard : ATBase() {

    /** Проверка отображения формы добавления карточки */
    @Test
    fun viewAddNewCardForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.viewAddCardForm()
    }

    /** Отображение ошибок валидации */
    @Test
    fun checkShowErrorValidate() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.viewValidateError()
    }

    /** Скрытие ошибок валидации */
    @Test
    fun checkHideErrorValidate() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.hideValidateError()
    }

    /** Проверка возврата на предыдущую форму */
    @Test
    fun checkJumpBackForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.checkBackForm()
    }

    /** Проверка добавления новой карточки */
    @Test
    fun checkAddNewCard() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.viewSmallCardForm(Data.HEAD.value, Data.QUEST.value)
    }

    /** Проверка формы редактирования карточки */
    @Test
    fun viewEditExistCardForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openEditCardForm()
        ATCardScreen.viewEditCardForm(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
    }

    /** Проверка редактирования карточки */
    @Test
    fun checkEditExistCard() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openEditCardForm()
        ATCardScreen.fillAddCard(Data.QUEST.value, Data.HEAD.value, Data.ANSWER.value)
        ATHomeScreen.viewSmallCardForm(Data.QUEST.value, Data.HEAD.value)
    }

    /** Отображение ошибок валидации на форме редактирования */
    @Test
    fun checkShowErrorValidateEditForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openEditCardForm()
        ATCardScreen.clearForm()
        ATCardScreen.viewValidateError()
    }

}

