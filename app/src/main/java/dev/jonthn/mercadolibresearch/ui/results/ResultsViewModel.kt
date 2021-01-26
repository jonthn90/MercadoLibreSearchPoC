package dev.jonthn.mercadolibresearch.ui.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.ui.common.Event
import dev.jonthn.usescases.SearchItems
import kotlinx.coroutines.launch
import timber.log.Timber

class ResultsViewModel(private val textSearch: String, private val searchItems: SearchItems) :
    ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _itemsSearch = MutableLiveData<List<ItemSearch>>()
    val itemsSearch: LiveData<List<ItemSearch>> get() = _itemsSearch

    private val _navigateToItem = MutableLiveData<Event<String>>()
    val navigateToItem: LiveData<Event<String>> get() = _navigateToItem

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean> get() = _empty

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {

            _loading.value = true
            _itemsSearch.value = searchItems.invoke(textSearch.trim().replace(" ", "%20"))
            _empty.value = _itemsSearch.value.isNullOrEmpty()
            _loading.value = false

            Timber.d("""++++++++++++++++++++++++++++++++++++ ${_itemsSearch.value?.toString()}""")
        }
    }

    fun onItemClicked(id: String) {
        _navigateToItem.value = Event(id)
    }
}