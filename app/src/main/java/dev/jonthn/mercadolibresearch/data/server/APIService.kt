package dev.jonthn.mercadolibresearch.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("/sites/MLM/search?")
    suspend fun searchItems(
        @Query("q") textSearch: String
    ): APIResponse
}