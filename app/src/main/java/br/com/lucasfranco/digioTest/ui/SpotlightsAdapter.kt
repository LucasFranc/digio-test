package br.com.lucasfranco.digioTest.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.lucasfranco.digioTest.R
import br.com.lucasfranco.digioTest.model.Spotlight
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_holder_spotlights.view.*



class SpotlightsAdapter(private val spotlights: List<Spotlight>) : RecyclerView.Adapter<SpotlightsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_spotlights, parent, false)
        val width = parent.width
        val params = view.layoutParams
        params.width = (width * 0.9).toInt()
        view.layoutParams = params
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(spotlights[position].bannerURL).into(holder.imgSpotlight)
    }

    override fun getItemCount(): Int {
        return spotlights.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgSpotlight = itemView.img_spotlight!!
    }

}