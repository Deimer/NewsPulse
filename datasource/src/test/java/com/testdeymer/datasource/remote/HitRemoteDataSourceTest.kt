package com.testdeymer.datasource.remote

import com.testdeymer.datasource.data.generateDummyBaseResponseDTO
import com.testdeymer.datasource.data.generateDummyBaseResponseDTOWithPartialData
import com.testdeymer.datasource.data.generateDummyBaseResponseWithList
import com.testdeymer.network.api.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HitRemoteDataSourceTest {

    @Mock
    private lateinit var mockApiService: ApiService

    private lateinit var hitRemoteDataSource: HitRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        hitRemoteDataSource = HitRemoteDataSource(mockApiService)
    }

    @Test
    fun `getHits should return list of hits`() = runTest {
        val expectedResponse = generateDummyBaseResponseDTO()
        `when`(mockApiService.searchByDate()).thenReturn(expectedResponse)
        val result = hitRemoteDataSource.getHits()
        assertEquals(expectedResponse.results, result)
        assertEquals(expectedResponse.results.size, result.size)
        assertEquals(expectedResponse.results[0].objectID, result[0].objectID)
    }

    @Test
    fun `getHits should return empty list when API response is empty`() = runTest {
        val expectedResponse = generateDummyBaseResponseWithList()
        `when`(mockApiService.searchByDate()).thenReturn(expectedResponse)
        val result = hitRemoteDataSource.getHits()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `getHits should throw exception when API fails`() = runTest {
        val expectedException = RuntimeException("Network error")
        `when`(mockApiService.searchByDate()).thenThrow(expectedException)
        try {
            hitRemoteDataSource.getHits()
            fail("Expected exception was not thrown")
        } catch (e: Exception) {
            assertEquals(expectedException, e)
        }
    }

    @Test
    fun `getHits should handle partial data gracefully`() = runTest {
        val expectedResponse = generateDummyBaseResponseDTOWithPartialData()
        `when`(mockApiService.searchByDate()).thenReturn(expectedResponse)
        val result = hitRemoteDataSource.getHits()
        assertEquals(expectedResponse.results.size, result.size)
        assertNull(result[0].storyTitle)
        assertEquals("Partial Dummy Story", result[1].storyTitle)
    }
}