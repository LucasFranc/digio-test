package br.com.lucasfranco.digioTest.callbacks

import br.com.lucasfranco.digioTest.model.Options

interface OptionsCallback {

    fun onResponse(response : Options)
    fun onError(message : String)

}