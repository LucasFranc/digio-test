package br.com.lucasfranco.digioTest.interactor

import br.com.lucasfranco.digioTest.callbacks.OptionsCallback
import br.com.lucasfranco.digioTest.service.MainService
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.utils.RetrofitClient
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class MainInteractor : MainInteractorInterface {

    override fun getOptions(): Single<Options> {
        return RetrofitClient.getClient()
                .create(MainService::class.java)
                .getOptions()
                .map {it.body()}
    }

}

interface MainInteractorInterface {

    fun getOptions(): Single<Options>
}
