package dev.jonthn.data.repository

import dev.jonthn.data.source.RemoteDataSource
import dev.jonthn.domain.Item
import dev.jonthn.domain.ItemSearch

class ItemsSearchRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun searchItems(textSearch: String): List<ItemSearch> {
        return remoteDataSource.searchItems(textSearch)
    }

    suspend fun getItem(ids: String): Item? {
        return remoteDataSource.getItem(ids)
    }
}