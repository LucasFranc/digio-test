package br.com.lucasfranco.digioTest.interactor

import br.com.lucasfranco.digioTest.service.MainService
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.utils.RetrofitClient
import io.reactivex.Single

class MainInteractorImpl : MainInteractor {

    override fun getOptions(): Single<Options> {
        return RetrofitClient.getClient()
                .create(MainService::class.java)
                .getOptions()
                .map {it.body()}
    }
}

interface MainInteractor {
    fun getOptions(): Single<Options>
}
