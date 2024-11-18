package com.testdeymer.network

import com.testdeymer.network.api.ApiService
import com.testdeymer.network.constants.NetworkConstants.Tags.TAG_MOBILE
import com.testdeymer.network.data.generateDummyBaseResponse
import com.testdeymer.network.data.generateDummyBaseResponseWithList
import com.testdeymer.network.data.generateDummyHitListDTO
import com.testdeymer.network.data.generateLargeDummyHitDTOList
import com.testdeymer.network.dto.BaseResponseDTO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ApiServiceTest {

    @Mock
    private lateinit var mockApiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `searchByDate returns expected data`() = runTest {
        val dummyHits = generateDummyHitListDTO()
        val expectedResponse = BaseResponseDTO(
            exhaustive = null,
            exhaustiveNbHits = "",
            exhaustiveTypo = null,
            results = dummyHits
        )
        `when`(mockApiService.searchByDate(query = TAG_MOBILE)).thenReturn(expectedResponse)
        val response = mockApiService.searchByDate(query = TAG_MOBILE)
        assertEquals(dummyHits.size, response.results.size)
        assertEquals(dummyHits[0].objectID, response.results[0].objectID)
        assertEquals(dummyHits[0].author, response.results[0].author)
        assertEquals(dummyHits[0].commentText, response.results[0].commentText)
    }

    @Test
    fun `searchByDate returns empty results`() = runTest {
        val expectedResponse = generateDummyBaseResponse(emptyResults = true)
        `when`(mockApiService.searchByDate(query = TAG_MOBILE)).thenReturn(expectedResponse)
        val response = mockApiService.searchByDate(query = TAG_MOBILE)
        assertTrue(response.results.isEmpty())
    }

    @Test
    fun `searchByDate with different query returns expected data`() = runTest {
        val dummyHits = generateDummyHitListDTO()
        val expectedResponse = generateDummyBaseResponse()
        val customQuery = "android"
        `when`(mockApiService.searchByDate(query = customQuery)).thenReturn(expectedResponse)
        val response = mockApiService.searchByDate(query = customQuery)
        assertEquals(dummyHits.size, response.results.size)
        assertEquals(dummyHits[0].objectID, response.results[0].objectID)
    }

    @Test
    fun `searchByDate handles API error`() = runTest {
        val exception = RuntimeException("API error")
        `when`(mockApiService.searchByDate(query = TAG_MOBILE)).thenThrow(exception)
        try {
            mockApiService.searchByDate(query = TAG_MOBILE)
            fail("Expected exception to be thrown")
        } catch (e: Exception) {
            assertEquals("API error", e.message)
        }
    }

    @Test
    fun `searchByDate returns large number of results`() = runTest {
        val largeDummyHits = generateLargeDummyHitDTOList()
        val expectedResponse = generateDummyBaseResponseWithList(largeDummyHits)
        `when`(mockApiService.searchByDate(query = TAG_MOBILE)).thenReturn(expectedResponse)
        val response = mockApiService.searchByDate(query = TAG_MOBILE)
        assertEquals(1000, response.results.size)
        assertEquals("Author999", response.results.last().author)
        assertEquals("Story Title 999", response.results.last().storyTitle)
    }

    @Test
    fun `searchByDate with special characters in query`() = runTest {
        val specialCharacterQuery = "mobile+%20&test=1"
        val dummyHits = generateDummyHitListDTO()
        val expectedResponse = generateDummyBaseResponseWithList(dummyHits)
        `when`(mockApiService.searchByDate(query = specialCharacterQuery)).thenReturn(expectedResponse)
        val response = mockApiService.searchByDate(query = specialCharacterQuery)
        assertEquals(dummyHits.size, response.results.size)
        assertEquals("Author1", response.results.first().author)
        assertEquals("Story Title 1", response.results.first().storyTitle)
    }
}
