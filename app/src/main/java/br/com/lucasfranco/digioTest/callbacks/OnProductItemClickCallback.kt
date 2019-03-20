package br.com.lucasfranco.digioTest.callbacks

import br.com.lucasfranco.digioTest.model.Product

interface OnProductItemClickCallback {
    fun onClick(product: Product)
}
