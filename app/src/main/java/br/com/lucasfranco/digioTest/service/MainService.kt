package br.com.lucasfranco.digioTest.service

import br.com.lucasfranco.digioTest.utils.Constants.PRODUCTS
import br.com.lucasfranco.digioTest.utils.Constants.SANDBOX
import br.com.lucasfranco.digioTest.model.Options
import retrofit2.Call
import retrofit2.http.GET

interface MainService {

    @GET(SANDBOX + PRODUCTS)
    fun getOptions() : Call<Options>

}