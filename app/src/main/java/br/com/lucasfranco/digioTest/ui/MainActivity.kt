package br.com.lucasfranco.digioTest.ui

import android.app.Dialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import br.com.lucasfranco.digioTest.view.MainActivityView
import br.com.lucasfranco.digioTest.presenter.MainPresenter
import br.com.lucasfranco.digioTest.R
import br.com.lucasfranco.digioTest.callbacks.OnProductItemClickCallback
import br.com.lucasfranco.digioTest.callbacks.OnSpotlightItemClickCallback
import br.com.lucasfranco.digioTest.interactor.MainInteractorImpl
import br.com.lucasfranco.digioTest.model.Item
import br.com.lucasfranco.digioTest.model.Product
import br.com.lucasfranco.digioTest.model.Spotlight
import br.com.lucasfranco.digioTest.utils.DialogLoading
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView, OnSpotlightItemClickCallback, OnProductItemClickCallback {

    private val presenter = MainPresenter( MainInteractorImpl())
    private lateinit var loadingDialog : Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingDialog = DialogLoading().bind(this)
        presenter.attachView(this)
        presenter.doRequestOptions()
    }

    override fun bindCash(cash: Spotlight) {
        Glide.with(this).load(cash.bannerURL).into(img_cash)
        img_cash.setOnClickListener { onClick(cash) }
    }

    override fun bindProducts(products: List<Product>) {
        rv_products.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_products.adapter = ProductsAdapter(products,this)
    }

    override fun bindSpotlight(spotlights: List<Spotlight>) {
        rv_spotlights.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_spotlights.adapter = SpotlightsAdapter(spotlights,this)
    }

    override fun showToast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onClick(spotlight: Spotlight) {
        val i = Intent(this@MainActivity,DetailsActivity::class.java)
        i.putExtra("item", Item(spotlight.name,spotlight.bannerURL,spotlight.description))
        startActivity(i)
    }
    override fun onClick(product: Product) {
        val i = Intent(this@MainActivity,DetailsActivity::class.java)
        i.putExtra("item", Item(product.name,product.imageURL,product.description))
        startActivity(i)
    }

    override fun showLoading(){loadingDialog.show()}
    override fun hideLoading(){loadingDialog.dismiss()}
}
