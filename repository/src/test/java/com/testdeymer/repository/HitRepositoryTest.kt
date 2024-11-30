package com.testdeymer.repository

import com.testdeymer.database.entities.HitEntity
import com.testdeymer.datasource.local.IHitLocalDataSource
import com.testdeymer.datasource.remote.IHitRemoteDataSource
import com.testdeymer.repository.data.generateDummyHitEntity
import com.testdeymer.repository.data.generateDummyHitEntityList
import com.testdeymer.repository.domain.HitDomain
import com.testdeymer.repository.mappers.toDomain
import com.testdeymer.repository.repositories.HitRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class HitRepositoryTest {

    @Mock
    private lateinit var mockHitRemoteDataSource: IHitRemoteDataSource

    @Mock
    private lateinit var mockHitLocalDataSource: IHitLocalDataSource

    private lateinit var hitRepository: HitRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        hitRepository = HitRepository(mockHitRemoteDataSource, mockHitLocalDataSource)
    }

    @Test
    fun `fetchAllHits emits local data when available`() = runTest {
        val dummyHits = generateDummyHitEntityList()
        `when`(mockHitLocalDataSource.fetchHits()).thenReturn(dummyHits)
        val results = mutableListOf<Result<List<HitDomain>>>()
        hitRepository.fetchAllHits().toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isSuccess)
        assertEquals(dummyHits.map { it.toDomain() }, results.first().getOrNull())
        verify(mockHitLocalDataSource).fetchHits()
    }

    @Test
    fun `fetchAllHits uses only local data when available`() = runTest {
        val dummyHits = generateDummyHitEntityList()
        `when`(mockHitLocalDataSource.fetchHits()).thenReturn(dummyHits)
        val results = mutableListOf<Result<List<HitDomain>>>()
        hitRepository.fetchAllHits().toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isSuccess)
        assertEquals(dummyHits.map { it.toDomain() }, results.first().getOrNull())
        verify(mockHitLocalDataSource).fetchHits()
        verifyNoInteractions(mockHitRemoteDataSource)
    }

    @Test
    fun `fetchAllHits emits failure when local fetch throws an exception`() = runTest {
        val exception = RuntimeException("Local data source error")
        `when`(mockHitLocalDataSource.fetchHits()).thenThrow(exception)
        val results = mutableListOf<Result<List<HitDomain>>>()
        hitRepository.fetchAllHits().toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isFailure)
        assertEquals(exception, results.first().exceptionOrNull())
        verify(mockHitLocalDataSource).fetchHits()
        verifyNoInteractions(mockHitRemoteDataSource)
    }

    @Test
    fun `fetchAllHits emits failure when remote fetch throws an exception`() = runTest {
        val dummyHits = emptyList<HitEntity>()
        val exception = RuntimeException("Remote data source error")
        `when`(mockHitLocalDataSource.fetchHits()).thenReturn(dummyHits)
        `when`(mockHitRemoteDataSource.getHits()).thenThrow(exception)
        val results = mutableListOf<Result<List<HitDomain>>>()
        hitRepository.fetchAllHits().toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isFailure)
        assertEquals(exception, results.first().exceptionOrNull())
        verify(mockHitLocalDataSource).fetchHits()
        verify(mockHitRemoteDataSource).getHits()
    }

    @Test
    fun `getHitById emits the correct hit when available`() = runTest {
        val dummyHit = generateDummyHitEntity(objectId = "1")
        `when`(mockHitLocalDataSource.fetchHitById("1")).thenReturn(dummyHit)
        val results = mutableListOf<Result<HitDomain>>()
        hitRepository.getHitById("1").toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isSuccess)
        assertEquals(dummyHit.toDomain(), results.first().getOrNull())
        verify(mockHitLocalDataSource).fetchHitById("1")
    }

    @Test
    fun `getHitById emits error when hit is not available`() = runTest {
        `when`(mockHitLocalDataSource.fetchHitById("1")).thenReturn(null)
        val results = mutableListOf<Result<HitDomain>>()
        hitRepository.getHitById("1").toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isFailure)
        assertTrue(results.first().exceptionOrNull() is IllegalArgumentException)
        verify(mockHitLocalDataSource).fetchHitById("1")
    }

    @Test
    fun `deleteHitById emits success when deletion is completed`() = runTest {
        val objectId = "1"
        val results = mutableListOf<Result<Unit>>()
        hitRepository.deleteHitById(objectId).toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isSuccess)
        assertEquals(Unit, results.first().getOrNull())
        verify(mockHitLocalDataSource).deleteHit(objectId)
    }

    @Test
    fun `deleteHitById emits error when deletion fails`() = runTest {
        val objectId = "1"
        val exception = RuntimeException("Deletion failed")
        `when`(mockHitLocalDataSource.deleteHit(objectId)).thenThrow(exception)
        val results = mutableListOf<Result<Unit>>()
        hitRepository.deleteHitById(objectId).toList(results)
        assertEquals(1, results.size)
        assertTrue(results.first().isFailure)
        assertEquals(exception, results.first().exceptionOrNull())
        verify(mockHitLocalDataSource).deleteHit(objectId)
    }
}