package dev.jonthn.mercadolibresearch.data.server

data class APIResponse(
    val results: List<ItemSearch>
)

data class ItemSearch(
    val id: String,
    val permalink: String,
    val price: Double,
    val thumbnail: String,
    val title: String
)