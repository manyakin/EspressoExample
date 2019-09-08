package ru.anikey.mymindcards.presenters

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.anikey.mymindcards.app.App
import ru.anikey.mymindcards.models.CardModel
import ru.anikey.mymindcards.repositories.IMainRepository
import ru.anikey.mymindcards.views.AddCardView
import javax.inject.Inject

@InjectViewState
class AddCardPresenter : MvpPresenter<AddCardView>() {
    private val disposes = CompositeDisposable()

    @Inject
    lateinit var mainRepository: IMainRepository

    init {
        App.appComponent.inject(this@AddCardPresenter)
    }

    fun saveCard(title: String, question: String, answer: String) {
        val disposable = mainRepository.addCard(title, question, answer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { card ->
                viewState.cardSaved(card)
            }
        disposes.add(disposable)
    }

    fun editCard(card: CardModel, title: String, question: String, answer: String) {
        val disposable = mainRepository.editCard(card, title, question, answer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { savedCard ->
                viewState.cardSaved(savedCard)
            }
        disposes.add(disposable)
    }

    fun dispose() {
        disposes.dispose()
    }
}