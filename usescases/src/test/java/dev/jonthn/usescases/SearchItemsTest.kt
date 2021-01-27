package dev.jonthn.usescases

import com.nhaarman.mockitokotlin2.whenever
import dev.jonthn.data.repository.ItemsSearchRepository
import dev.jonthn.testshared.mockedItemSearch
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchItemsTest {

    @Mock
    lateinit var itemsSearchRepository: ItemsSearchRepository

    lateinit var searchItems: SearchItems

    @Before
    fun setUp() {
        searchItems = SearchItems(itemsSearchRepository)
    }

    @Test
    fun `invoke calls repository`() {
        runBlocking {
            val items = listOf(mockedItemSearch.copy("12345"))
            whenever(itemsSearchRepository.searchItems("nintendo")).thenReturn(items)
            val result = searchItems.invoke("nintendo")
            assertEquals(items, result)
        }
    }
}