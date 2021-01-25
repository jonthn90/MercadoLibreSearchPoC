package dev.jonthn.mercadolibresearch.ui.results

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.R
import dev.jonthn.mercadolibresearch.databinding.ViewItemSearchBinding
import dev.jonthn.mercadolibresearch.ui.common.basicDiffUtil
import dev.jonthn.mercadolibresearch.ui.common.bindingInflate

class ResultsAdapter (private val listener: (String) -> Unit): RecyclerView.Adapter<ResultsAdapter.ViewHolder>(){

    var itemsSearch: List<ItemSearch> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.bindingInflate(R.layout.view_item_search, false))

    override fun getItemCount(): Int = itemsSearch.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemSearch = itemsSearch[position]
        holder.dataBinding.itemSearch = itemSearch
        holder.itemView.setOnClickListener { listener(itemSearch.id) }
    }

    class ViewHolder(val dataBinding: ViewItemSearchBinding) : RecyclerView.ViewHolder(dataBinding.root)

}