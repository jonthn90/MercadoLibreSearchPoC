package dev.jonthn.data.source

import dev.jonthn.domain.ItemSearch

interface RemoteDataSource {
    suspend fun searchItems(textSearch: String): List<ItemSearch>
}