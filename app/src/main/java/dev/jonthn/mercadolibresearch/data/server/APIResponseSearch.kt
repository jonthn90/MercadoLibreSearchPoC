package dev.jonthn.mercadolibresearch.data.server

data class APIResponseSearch(
    val status: Int,
    val error: String,
    val message: String,
    val results: List<ItemSearch>
)

data class ItemSearch(
    val id: String,
    val permalink: String,
    val price: Double,
    val thumbnail: String,
    val title: String
)