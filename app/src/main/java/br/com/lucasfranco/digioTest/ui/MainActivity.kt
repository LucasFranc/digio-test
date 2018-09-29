package br.com.lucasfranco.digioTest.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import br.com.lucasfranco.digioTest.view.MainActivityView
import br.com.lucasfranco.digioTest.presenter.MainPresenter
import br.com.lucasfranco.digioTest.R
import br.com.lucasfranco.digioTest.model.Cash
import br.com.lucasfranco.digioTest.model.Product
import br.com.lucasfranco.digioTest.model.Spotlight
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private val presenter = MainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        presenter.doRequestOptions()
    }

    override fun bindCash(cash: Cash) {
        Glide.with(this).load(cash.bannerURL).into(img_cash)
    }

    override fun bindProducts(products: List<Product>) {
        rv_products.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_products.adapter = ProductsAdapter(products)
    }

    override fun bindSpotlight(spotlights: List<Spotlight>) {
        rv_spotlights.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_spotlights.adapter = SpotlightsAdapter(spotlights)
    }

}
