package dev.jonthn.data.repository

import com.nhaarman.mockitokotlin2.whenever
import dev.jonthn.data.source.RemoteDataSource
import dev.jonthn.testshared.mockedItem
import dev.jonthn.testshared.mockedItemSearch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ItemsSearchRepositoryTest {

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    lateinit var itemsSearchRepository: ItemsSearchRepository

    @Before
    fun setUp() {
        itemsSearchRepository = ItemsSearchRepository(remoteDataSource)
    }

    @Test
    fun `searchItems is called`() {
        runBlocking {
            val itemsSearch = listOf(mockedItemSearch.copy("12345"))
            whenever(itemsSearchRepository.searchItems("nintendo")).thenReturn(itemsSearch)
            val result = itemsSearchRepository.searchItems("nintendo")
            assertEquals(itemsSearch, result)
        }
    }

    @Test
    fun `getItem is called`() {
        runBlocking {
            val localItem = mockedItem
            whenever(itemsSearchRepository.getItem("12345")).thenReturn(localItem)
            val result = itemsSearchRepository.getItem("12345")
            assertEquals(localItem, result)
        }
    }

}