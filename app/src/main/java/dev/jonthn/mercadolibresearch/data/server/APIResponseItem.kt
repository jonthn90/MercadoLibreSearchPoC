package dev.jonthn.mercadolibresearch.data.server

data class APIResponseItem(
    val body: Body,
    val code: Int
)

data class Body(
    val accepts_mercadopago: Boolean,
    val available_quantity: Int,
    val condition: String,
    val permalink: String,
    val pictures: List<Picture>,
    val price: Double,
    val shipping: Shipping,
    val sold_quantity: Int,
    val status: String,
    val stop_time: String,
    val thumbnail: String,
    val title: String,
    val warranty: String
)

data class Picture(
    val secure_url: String
)

data class Shipping(
    val free_shipping: Boolean,
    val store_pick_up: Boolean
)
