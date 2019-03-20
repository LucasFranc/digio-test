package br.com.lucasfranco.digioTest.ui

import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.lucasfranco.digioTest.model.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import android.support.design.widget.AppBarLayout
import android.view.MenuItem
import br.com.lucasfranco.digioTest.R


class DetailsActivity : AppCompatActivity() {

    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        item = intent?.extras?.getParcelable("item") as Item
        collapsing_details.title = item.name
        collapsing_details.setCollapsedTitleTextColor(getColor(R.color.white))
        Glide.with(this).load(item.imageURL).into(img_details)
        txt_details.text = item.description
        bindToolbar()
        bindToolbarOffset()
    }

    private fun bindToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.navigationIcon?.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)
    }

    private fun bindToolbarOffset() {
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsing_details.title = item.name
                    isShow = true
                } else if (isShow) {
                    collapsing_details.title = " "
                    isShow = false
                }
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


}