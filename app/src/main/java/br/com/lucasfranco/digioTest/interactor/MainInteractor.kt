package br.com.lucasfranco.digioTest.interactor

import android.util.Log
import br.com.lucasfranco.digioTest.callbacks.OptionsCallback
import br.com.lucasfranco.digioTest.service.MainService
import br.com.lucasfranco.digioTest.model.Options
import br.com.lucasfranco.digioTest.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainInteractor {

    fun getOptions(callback : OptionsCallback){
        RetrofitClient.getClient().create(MainService::class.java).
                getOptions().enqueue(object : Callback<Options> {

            override fun onResponse(call: Call<Options>, response: Response<Options>) {
                if(response.isSuccessful){
                    callback.onResponse(response.body()!!)
                }else{
                    onFailure(call, Throwable("error"))
                }
            }

            override fun onFailure(call: Call<Options>, t: Throwable) {
                callback.onError(t.localizedMessage)
            }

        })
    }

}