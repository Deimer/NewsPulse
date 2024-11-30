package com.testdeymer.usecase

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.usecase.hit.DeleteHitByIdUseCase
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
class DeleteHitByIdUseCaseTest {

    @Mock
    private lateinit var mockHitRepository: IHitRepository

    private lateinit var deleteHitByIdUseCase: DeleteHitByIdUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        deleteHitByIdUseCase = DeleteHitByIdUseCase(mockHitRepository)
    }

    @Test
    fun `invoke should emit success result when repository deletes hit`() = runTest {
        val objectId = "123"
        val successFlow = flowOf(Result.success(Unit))
        `when`(mockHitRepository.deleteHitById(objectId)).thenReturn(successFlow)
        val results = mutableListOf<Result<Unit>>()
        deleteHitByIdUseCase.invoke(objectId).toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isSuccess)
        assertEquals(Unit, result.getOrNull())
        verify(mockHitRepository).deleteHitById(objectId)
    }

    @Test
    fun `invoke should emit error result when repository throws an exception`() = runTest {
        val objectId = "123"
        val exception = RuntimeException("Test exception")
        val errorFlow = flowOf(Result.failure<Unit>(exception))
        `when`(mockHitRepository.deleteHitById(objectId)).thenReturn(errorFlow)
        val results = mutableListOf<Result<Unit>>()
        deleteHitByIdUseCase.invoke(objectId).toList(results)
        assertEquals(1, results.size)
        val result = results.first()
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
        verify(mockHitRepository).deleteHitById(objectId)
    }
}
