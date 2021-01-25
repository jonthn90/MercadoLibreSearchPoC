package dev.jonthn.usescases

import dev.jonthn.data.repository.ItemsSearchRepository
import dev.jonthn.domain.ItemSearch

class SearchItems(private val itemsSearchRepository: ItemsSearchRepository) {
    suspend fun invoke(textSearch: String): List<ItemSearch> = itemsSearchRepository.searchItems(textSearch)
}