package dev.jonthn.data.source

import dev.jonthn.domain.Item
import dev.jonthn.domain.ItemSearch

interface RemoteDataSource {
    suspend fun searchItems(textSearch: String): List<ItemSearch>

    suspend fun getItem(idItem: String): Item?
}