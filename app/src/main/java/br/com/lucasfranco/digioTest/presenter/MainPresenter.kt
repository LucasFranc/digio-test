package br.com.lucasfranco.digioTest.presenter

import android.annotation.SuppressLint
import br.com.lucasfranco.digioTest.interactor.MainInteractor
import br.com.lucasfranco.digioTest.view.MainActivityView
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val interactor: MainInteractor,
                         private val mainThread: Scheduler = AndroidSchedulers.mainThread(),
                         private val ioTread: Scheduler = Schedulers.io()) {

    private lateinit var view: MainActivityView

    fun attachView(view: MainActivityView) { this.view = view }

    @SuppressLint("CheckResult")
    fun doRequestOptions() {
        interactor.getOptions()
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