package dev.jonthn.mercadolibresearch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jonthn.domain.Item
import dev.jonthn.usescases.GetItem
import kotlinx.coroutines.launch

class DetailViewModel(private val idItem: String, private val getItem: GetItem) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> get() = _item

    private val _empty = MutableLiveData<Boolean>()
    val empty: LiveData<Boolean> get() = _empty

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            _loading.value = true
            _item.value = getItem.invoke(idItem)
            _empty.value = _item.value == null
            _loading.value = false
        }
    }
}