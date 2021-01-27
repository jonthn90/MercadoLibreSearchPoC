package dev.jonthn.mercadolibresearch.data

import dev.jonthn.domain.Body
import dev.jonthn.domain.Picture
import dev.jonthn.domain.Shipping
import dev.jonthn.mercadolibresearch.data.server.ItemSearch as ServerItemSearch
import dev.jonthn.domain.ItemSearch as DomainItemSearch
import dev.jonthn.mercadolibresearch.data.server.APIResponseItem as ServerItem
import dev.jonthn.domain.Item as DomainItem

const val notDefined = "No definido"

fun ServerItemSearch.toDomainItemSearch(): DomainItemSearch =
    DomainItemSearch(id, permalink, price, thumbnail, title)

fun ServerItem.toDomainItem(): DomainItem =
    DomainItem(
        Body(
            body.accepts_mercadopago,
            body.available_quantity,
            body.condition ?: notDefined,
            body.id,
            body.permalink,
            body.pictures.map { Picture(it.secure_url) },
            body.price,
            Shipping(body.shipping.free_shipping, body.shipping.store_pick_up),
            body.sold_quantity,
            body.thumbnail,
            body.title,
            body.warranty ?: notDefined
        ), code
    )
