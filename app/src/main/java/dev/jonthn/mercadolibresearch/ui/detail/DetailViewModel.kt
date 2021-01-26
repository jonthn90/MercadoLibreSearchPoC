package dev.jonthn.mercadolibresearch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jonthn.domain.Item
import dev.jonthn.usescases.GetItem
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(private val idItem: String, private val getItem: GetItem) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _item = MutableLiveData<Item>()
    val item: LiveData<Item> get() = _item

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {

            _loading.value = true
            _item.value = getItem.invoke(idItem)
            _loading.value = false

            Timber.d("""++++++++++++++++++++++++++++++++++++ ${_item.value?.toString()}""")
        }
    }
}