package dev.jonthn.mercadolibresearch.data.server

import dev.jonthn.data.source.RemoteDataSource
import dev.jonthn.domain.ItemSearch
import dev.jonthn.mercadolibresearch.data.toDomainItemSearch

class APIDataSource(private val api: API) : RemoteDataSource {

    override suspend fun searchItems(textSearch: String): List<ItemSearch> =
        api.service
            .searchItems(textSearch)
            .results
            .map { it.toDomainItemSearch() }
}