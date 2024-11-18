package com.testdeymer.usecase

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.repository.utils.OnResult
import com.testdeymer.usecase.data.generateDummyHitDomainList
import com.testdeymer.usecase.hit.FetchHitByIdUseCase
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
    fun `invoke should return success result when repository returns hit domain`() = runTest {
        val objectId = "123"
        val expectedHitDomain = generateDummyHitDomainList().first()
        val successResult = OnResult.Success(expectedHitDomain)
        `when`(mockHitRepository.getHitById(objectId)).thenReturn(successResult)
        val result = fetchHitByIdUseCase.invoke(objectId)
        assertTrue(result is OnResult.Success)
        assertEquals(expectedHitDomain, (result as OnResult.Success).data)
        verify(mockHitRepository).getHitById(objectId)
    }

    @Test
    fun `invoke should return error result when repository throws an exception`() = runTest {
        val objectId = "123"
        val exception = Exception("Test exception")
        val errorResult = OnResult.Error(exception)
        `when`(mockHitRepository.getHitById(objectId)).thenReturn(errorResult)
        val result = fetchHitByIdUseCase.invoke(objectId)
        assertTrue(result is OnResult.Error)
        assertEquals(exception, (result as OnResult.Error).exception)
        verify(mockHitRepository).getHitById(objectId)
    }
}