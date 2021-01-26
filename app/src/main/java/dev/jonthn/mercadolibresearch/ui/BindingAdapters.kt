package dev.jonthn.mercadolibresearch.ui

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.ui.common.loadUrl
import dev.jonthn.mercadolibresearch.ui.results.ResultsAdapter
import java.text.NumberFormat

@BindingAdapter("urlCover")
fun ImageView.bindUrlCover(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("urlDetail")
fun ImageView.bindUrlDetail(url: String?) {
    if (url != null) loadUrl("https://image.tmdb.org/t/p/w780$url")
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
        "Envio gratuito"
    else
        "Más costo de envio"
}