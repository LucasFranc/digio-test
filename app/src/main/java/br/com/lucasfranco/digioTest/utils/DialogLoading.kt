package br.com.lucasfranco.digioTest.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import br.com.lucasfranco.digioTest.R
import kotlinx.android.synthetic.main.dialog_loading.*

/**
 * Created by lucas on 2/28/18.
 */
class DialogLoading {

    fun bind(context : Context) : Dialog {
        val dialog = Dialog(context, R.style.ThemeDialog)
        initDialog(dialog)
        bindItens(dialog)
        return dialog
    }

    private fun initDialog(dialog : Dialog) {
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.dialog_loading)
    }

    private fun bindItens(dialog : Dialog) {
        dialog.avi_loading_view.show()
    }
}