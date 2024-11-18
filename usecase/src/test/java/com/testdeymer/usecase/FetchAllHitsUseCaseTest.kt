package com.testdeymer.usecase

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.repository.utils.OnResult
import com.testdeymer.usecase.data.generateDummyHitDomainList
import com.testdeymer.usecase.hit.FetchAllHitsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FetchAllHitsUseCaseTest {

    @Mock
    private lateinit var mockHitRepository: IHitRepository

    private lateinit var fetchAllHitsUseCase: FetchAllHitsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fetchAllHitsUseCase = FetchAllHitsUseCase(mockHitRepository)
    }

    @Test
    fun `invoke should return success result when repository fetches hits`() = runTest {
        val dummyHits = generateDummyHitDomainList()
        val successResult = OnResult.Success(dummyHits)
        `when`(mockHitRepository.fetchAllHits()).thenReturn(successResult)
        val result = fetchAllHitsUseCase.invoke()
        assertTrue(result is OnResult.Success)
        assertEquals(dummyHits, (result as OnResult.Success).data)
        verify(mockHitRepository).fetchAllHits()
    }

    @Test
    fun `invoke should return error result when repository throws an exception`() = runTest {
        val exception = RuntimeException("Test exception")
        val errorResult = OnResult.Error(exception)
        `when`(mockHitRepository.fetchAllHits()).thenReturn(errorResult)
        val result = fetchAllHitsUseCase.invoke()
        assertTrue(result is OnResult.Error)
        assertEquals(exception, (result as OnResult.Error).exception)
        verify(mockHitRepository).fetchAllHits()
    }
}