package dev.jonthn.mercadolibresearch.data.server

import dev.jonthn.data.source.RemoteDataSource
import dev.jonthn.domain.Item
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.data.toDomainItem
import dev.jonthn.mercadolibresearch.data.toDomainItemSearch
import timber.log.Timber

class APIDataSource(private val api: API) : RemoteDataSource {

    override suspend fun searchItems(textSearch: String): List<ItemSearch> =
        try {
            api.service.searchItems(textSearch).results.map { it.toDomainItemSearch() }
        } catch (e: Exception) {
            Timber.d("searchItems Exception")
            e.printStackTrace()
            emptyList()
        }

    override suspend fun getItem(idItem: String): Item? =
        try {
           api.service.getItem(idItem)[0].toDomainItem()
        } catch (e: Exception) {
            Timber.d("getItem Exception")
            e.printStackTrace()
            null
        }
}