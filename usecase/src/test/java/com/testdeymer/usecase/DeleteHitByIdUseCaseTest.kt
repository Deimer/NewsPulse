package com.testdeymer.usecase

import com.testdeymer.repository.repositories.IHitRepository
import com.testdeymer.usecase.hit.DeleteHitByIdUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
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
    fun `invoke should successfully delete hit when repository completes without error`() = runTest {
        val objectId = "123"
        `when`(mockHitRepository.deleteHitById(objectId)).thenReturn(Unit)
        val result = deleteHitByIdUseCase.invoke(objectId)
        verify(mockHitRepository).deleteHitById(objectId)
        assertEquals(Unit, result)
    }

    @Test
    fun `invoke should propagate exception when repository throws an error`() = runTest {
        val objectId = "123"
        val exception = RuntimeException("Test exception")
        `when`(mockHitRepository.deleteHitById(objectId)).thenThrow(exception)
        try {
            deleteHitByIdUseCase.invoke(objectId)
            fail("Expected exception was not thrown")
        } catch (e: RuntimeException) {
            assertEquals(exception, e)
        }
        verify(mockHitRepository).deleteHitById(objectId)
    }
}