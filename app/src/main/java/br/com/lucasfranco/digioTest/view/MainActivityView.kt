package br.com.lucasfranco.digioTest.view

import br.com.lucasfranco.digioTest.model.Cash
import br.com.lucasfranco.digioTest.model.Product
import br.com.lucasfranco.digioTest.model.Spotlight

interface MainActivityView {
    fun bindCash(cash: Cash)
    fun bindProducts(products : List<Product>)
    fun bindSpotlight(spotlights: List<Spotlight>)
}
