package br.com.lucasfranco.digioTest.model

import com.google.gson.annotations.SerializedName

data class Options(
        @SerializedName("spotlight") val spotlights: List<Spotlight>,
        val products : List<Product>,
        val cash: Cash
)