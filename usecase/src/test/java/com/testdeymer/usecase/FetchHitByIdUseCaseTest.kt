package com.testdeymer.usecase

import com.testdeymer.repository.domain.HitDomain
import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.usecase.data.generateDummyHitDomainList
import com.testdeymer.usecase.hit.FetchHitByIdUseCase
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
class FetchHitByIdUseCaseTest {

    @Mock
    private lateinit var mockHitRepository: IHitRepository

    private lateinit var fetchHitByIdUseCase: FetchHitByIdUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fetchHitByIdUseCase = FetchHitByIdUseCase(mockHitRepository)
    }

    @Test
    fun `invoke should emit success result when repository returns hit domain`() = runTest {
        val objectId = "123"
        val expectedHitDomain = generateDummyHitDomainList().first()
        val successFlow = flowOf(Result.success(expectedHitDomain))
        `when`(mockHitRepository.getHitById(objectId)).thenReturn(successFlow)
        val results = mutableListOf<Result<HitDomain>>()
        fetchHitByIdUseCase(objectId).toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isSuccess)
        assertEquals(expectedHitDomain, result.getOrNull())
        verify(mockHitRepository).getHitById(objectId)
    }

    @Test
    fun `invoke should emit error result when repository throws an exception`() = runTest {
        val objectId = "123"
        val exception = Exception("Test exception")
        val errorFlow = flowOf(Result.failure<HitDomain>(exception))
        `when`(mockHitRepository.getHitById(objectId)).thenReturn(errorFlow)
        val results = mutableListOf<Result<HitDomain>>()
        fetchHitByIdUseCase(objectId).toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(mockHitRepository).getHitById(objectId)
    }
}