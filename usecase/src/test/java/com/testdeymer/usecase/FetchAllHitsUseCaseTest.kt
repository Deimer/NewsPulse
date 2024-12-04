package com.testdeymer.usecase

import com.testdeymer.repository.domain.HitDomain
import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.usecase.data.generateDummyHitDomainList
import com.testdeymer.usecase.hit.FetchAllHitsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
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
    fun `invoke should emit success result when repository fetches hits`() = runTest {
        val dummyHits = generateDummyHitDomainList()
        val successFlow = flowOf(Result.success(dummyHits))
        `when`(mockHitRepository.fetchAllHits()).thenReturn(successFlow)
        val results = mutableListOf<Result<List<HitDomain>>>()
        fetchAllHitsUseCase().toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isSuccess)
        assertEquals(dummyHits, result.getOrNull())
        verify(mockHitRepository).fetchAllHits()
    }

    @Test
    fun `invoke should emit error result when repository throws an exception`() = runTest {
        val exception = RuntimeException("Test exception")
        val errorFlow = flowOf(Result.failure<List<HitDomain>>(exception))
        `when`(mockHitRepository.fetchAllHits()).thenReturn(errorFlow)
        val results = mutableListOf<Result<List<HitDomain>>>()
        fetchAllHitsUseCase().toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(mockHitRepository).fetchAllHits()
    }
}