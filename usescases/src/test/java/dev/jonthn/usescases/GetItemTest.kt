package dev.jonthn.usescases

import com.nhaarman.mockitokotlin2.whenever
import dev.jonthn.data.repository.ItemsSearchRepository
import dev.jonthn.testshared.mockedItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetItemTest{

    @Mock
    lateinit var itemsSearchRepository: ItemsSearchRepository

    lateinit var getItem: GetItem

    @Before
    fun setUp() {
        getItem = GetItem(itemsSearchRepository)
    }

    @Test
    fun `invoke calls repository`() {
        runBlocking {
            val item= mockedItem
            whenever(itemsSearchRepository.getItem("12345")).thenReturn(item)
            val result = getItem.invoke("12345")
            assertEquals(item, result)
        }
    }


}