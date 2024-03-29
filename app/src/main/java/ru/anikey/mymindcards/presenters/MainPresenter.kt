package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.anikey.mymindcards.R
import ru.anikey.mymindcards.app.App
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.IMainRepository
import ru.anikey.mymindcards.views.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private val disposes = CompositeDisposable()

    @Inject
    lateinit var mainRepository: IMainRepository

    init {
        App.appComponent.inject(this@MainPresenter)
    }

    fun initCardList() {
        val disposable = mainRepository.getCardList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cardList ->
                viewState.showList(cardList)
            }
        disposes.add(disposable)
    }

    fun addButtonPressed() {
        viewState.startAddCardActivity()
    }

    fun startTestPressed() {
        val disposable = mainRepository.getCardList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { cardsList ->
                if (cardsList.size >= 1) {
                    viewState.startTest()
                } else {
                    viewState.showError(R.string.main_no_cards_for_test)
                }
            }
        disposes.add(disposable)
    }

    fun getClickedCard(position: Int) {
        val disposable = mainRepository.getCard(position)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { card ->
                viewState.startEditCardActivity(card, position)
            }
        disposes.add(disposable)
    }

    fun deleteCard(card: CardModel) {
        mainRepository.deleteCard(card)
    }

    fun dispose() {
        disposes.dispose()
    }

}
