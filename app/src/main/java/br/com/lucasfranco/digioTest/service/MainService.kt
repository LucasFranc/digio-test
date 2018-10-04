package br.com.lucasfranco.digioTest.service

import br.com.lucasfranco.digioTest.utils.Constants.PRODUCTS
import br.com.lucasfranco.digioTest.utils.Constants.SANDBOX
import br.com.lucasfranco.digioTest.model.Options
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface MainService {

    @GET(SANDBOX + PRODUCTS)
    fun getOptions() : Single<Response<Options>>

}