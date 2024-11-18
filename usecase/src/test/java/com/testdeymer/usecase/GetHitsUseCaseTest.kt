package com.testdeymer.usecase

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.repository.utils.OnResult
import com.testdeymer.usecase.data.generateDummyHitDomainList
import com.testdeymer.usecase.hit.GetHitsUseCase
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
class GetHitsUseCaseTest {

    @Mock
    private lateinit var mockHitRepository: IHitRepository

    private lateinit var getHitsUseCase: GetHitsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        getHitsUseCase = GetHitsUseCase(mockHitRepository)
    }

    @Test
    fun `invoke should return success result when repository returns hit domains`() = runTest {
        val expectedHits = generateDummyHitDomainList()
        val successResult = OnResult.Success(expectedHits)
        `when`(mockHitRepository.getHits()).thenReturn(successResult)
        val result = getHitsUseCase.invoke()
        assertTrue(result is OnResult.Success)
        assertEquals(expectedHits, (result as OnResult.Success).data)
        verify(mockHitRepository).getHits()
    }

    @Test
    fun `invoke should return error result when repository throws an exception`() = runTest {
        val exception = Exception("Test exception")
        val errorResult = OnResult.Error(exception)
        `when`(mockHitRepository.getHits()).thenReturn(errorResult)
        val result = getHitsUseCase.invoke()
        assertTrue(result is OnResult.Error)
        assertEquals(exception, (result as OnResult.Error).exception)
        verify(mockHitRepository).getHits()
    }
}