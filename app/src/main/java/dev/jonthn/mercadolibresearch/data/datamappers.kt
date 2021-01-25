package dev.jonthn.mercadolibresearch.data

import dev.jonthn.mercadolibresearch.data.server.ItemSearch as ServerItemSearch
import dev.jonthn.domain.ItemSearch as DomainItemSearch

fun ServerItemSearch.toDomainItemSearch(): DomainItemSearch =
    DomainItemSearch(id,permalink, price, thumbnail, title)