package dev.jonthn.usescases

import dev.jonthn.data.repository.ItemsSearchRepository
import dev.jonthn.domain.Item

class GetItem(private val itemsSearchRepository: ItemsSearchRepository) {
    suspend fun invoke(idItem: String): Item? = itemsSearchRepository.getItem(idItem)
}