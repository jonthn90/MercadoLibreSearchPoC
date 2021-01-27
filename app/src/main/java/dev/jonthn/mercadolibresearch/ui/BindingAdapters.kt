package dev.jonthn.mercadolibresearch.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.R
import dev.jonthn.mercadolibresearch.ui.common.loadUrl
import dev.jonthn.mercadolibresearch.ui.results.ResultsAdapter
import java.text.NumberFormat

@BindingAdapter("urlCover")
fun ImageView.bindUrlCover(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = visible?.let {
        if (visible) View.VISIBLE else View.GONE
    } ?: View.GONE
}

@BindingAdapter("items")
fun RecyclerView.setItems(movies: List<ItemSearch>?) {
    (adapter as? ResultsAdapter)?.let {
        it.itemsSearch = movies ?: emptyList()
    }
}

@BindingAdapter("price")
fun MaterialTextView.setPrice(price: Double) {
    text = NumberFormat.getCurrencyInstance().format(price);
}

@BindingAdapter("shipping")
fun MaterialTextView.setshipping(isFree: Boolean) {
    text = if (isFree)
        context.getString(R.string.free_shipping)
    else
        context.getString(R.string.not_free_shipping)
}

@BindingAdapter("condition")
fun MaterialTextView.setcondition(cond: String?) {

    text = when {
        cond.isNullOrEmpty() -> ""
        cond == "new" -> context.getString(R.string.new_item)
        else -> context.getString(R.string.used)
    }
}

@BindingAdapter("mercado_pago")
fun MaterialTextView.setMercadoPago(isMercadoPago: Boolean) {
    text = if (isMercadoPago)
        context.getString(R.string.accepts_mercado_pago)
    else
        context.getString(R.string.not_accepts_mercado_pago)
}

@BindingAdapter("available_quantity")
fun MaterialTextView.setAvailableQuantity(availableQuantity: Int) {
    text = when (availableQuantity) {
        0 -> context.getString(R.string.not_available)
        1 -> context.getString(R.string.one_available)
        else -> "Aún hay $availableQuantity disponibles"
    }
}

@BindingAdapter("sold_quantity")
fun MaterialTextView.setSoldQuantity(soldQuantity: Int) {
    text = when (soldQuantity) {
        0 -> context.getString(R.string.not_sold)
        else -> "Hasta ahora se han vendido $soldQuantity"
    }
}