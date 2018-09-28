package br.com.lucasfranco.digioTest.presenter

import br.com.lucasfranco.digioTest.callbacks.OptionsCallback
import br.com.lucasfranco.digioTest.interactor.MainInteractor
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.view.MainActivityView

class MainPresenter : OptionsCallback {

    private lateinit var view: MainActivityView

    fun attachView(view: MainActivityView) { this.view = view }
    fun doRequestOptions() {
        MainInteractor().getOptions(this)
    }

    override fun onResponse(response: Options) {
        view.bindCash(response.cash)
    }
    override fun onError(message: String) {

    }


}