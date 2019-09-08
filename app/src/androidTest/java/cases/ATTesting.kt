package cases

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import base.ATBase
import base.Data
import org.junit.Test
import org.junit.runner.RunWith
import screen.ATCardScreen
import screen.ATHomeScreen
import screen.ATTestingScreen


@RunWith(AndroidJUnit4ClassRunner::class)
class ATTesting : ATBase() {

    /** Отображение формы тестирования с вопросом */
    @Test
    fun checkTestingFormWithQuest() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openTestingForm()
        ATTestingScreen.viewTestingFormWithQuest(Data.HEAD.value, Data.QUEST.value)
    }

    /** Отображение формы тестирования с ответом */
    @Test
    fun checkTestingFormWithAnswer() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openTestingForm()
        ATTestingScreen.viewTestingFormWithAnswer(Data.HEAD.value)
    }

    /** Проверка возврата на предыдущую форму */
    @Test
    fun checkBackForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openTestingForm()
        ATTestingScreen.checkBackForm()
    }

    /** Проверка отображения формы с ответами */
    @Test
    fun checkResultAlertForm() {
        ATHomeScreen.openAddCardForm()
        ATCardScreen.fillAddCard(Data.HEAD.value, Data.QUEST.value, Data.ANSWER.value)
        ATHomeScreen.openTestingForm()
        ATTestingScreen.viewResultAlertForm()
    }

}
