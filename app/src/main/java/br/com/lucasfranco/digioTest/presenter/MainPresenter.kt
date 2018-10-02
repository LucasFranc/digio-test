package br.com.lucasfranco.digioTest.presenter

import br.com.lucasfranco.digioTest.callbacks.OptionsCallback
import br.com.lucasfranco.digioTest.interactor.MainInteractor
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.view.MainActivityView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter : OptionsCallback {

    private lateinit var view: MainActivityView

    fun attachView(view: MainActivityView) { this.view = view }
    fun doRequestOptions() {
        view.showLoading()
        MainInteractor().getOptions(this)
    }

    override fun onResponse(response: Options) {
        view.hideLoading()
        view.bindSpotlight(response.spotlights)
        view.bindCash(response.cash)
        view.bindProducts(response.products)
    }
    override fun onError(message: String) {
        view.hideLoading()
        view.showToast(message)
    }


}