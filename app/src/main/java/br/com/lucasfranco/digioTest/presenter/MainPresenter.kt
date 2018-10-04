package br.com.lucasfranco.digioTest.presenter

import android.support.annotation.MainThread
import br.com.lucasfranco.digioTest.interactor.MainInteractorInterface
import br.com.lucasfranco.digioTest.view.MainActivityView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class MainPresenter(val interactor: MainInteractorInterface,
                         val mainThread: Scheduler = AndroidSchedulers.mainThread(),
                         val ioTread: Scheduler = Schedulers.io()) {

    private lateinit var view: MainActivityView

    fun attachView(view: MainActivityView) {
        this.view = view
    }

    fun doRequestOptions() {
        interactor
                .getOptions()
                .observeOn(mainThread)
                .subscribeOn(ioTread)
                .doOnSubscribe { view.showLoading() }
                .doFinally { view.hideLoading() }
                .subscribe({
                    view.bindSpotlight(it.spotlights)
                    view.bindCash(it.cash)
                    view.bindProducts(it.products)
                }, {
                    view.showToast(it.localizedMessage)
                })
    }

}