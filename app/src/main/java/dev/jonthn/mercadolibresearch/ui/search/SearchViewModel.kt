package dev.jonthn.mercadolibresearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    val textSearch = MutableLiveData<String>()

    val error: LiveData<Boolean> = Transformations.map(textSearch) {
        it.isNullOrEmpty()
    }

    init {
        textSearch.value = "Motorola g6"
    }

    fun isEmptySearch(): Boolean {
        return (textSearch.value.isNullOrEmpty())
    }
}