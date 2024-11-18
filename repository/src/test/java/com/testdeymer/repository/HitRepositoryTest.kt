package com.testdeymer.repository

import com.testdeymer.datasource.local.IHitLocalDataSource
import com.testdeymer.datasource.remote.IHitRemoteDataSource
import com.testdeymer.repository.data.generateDummyHitEntity
import com.testdeymer.repository.data.generateDummyHitEntityList
import com.testdeymer.repository.mappers.toDomain
import com.testdeymer.repository.repositories.HitRepository
import com.testdeymer.repository.utils.OnResult
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
    fun `fetchAllHits returns local data when available`() = runTest {
        val dummyHits = generateDummyHitEntityList()
        `when`(mockHitLocalDataSource.fetchHits()).thenReturn(dummyHits)
        val result = hitRepository.fetchAllHits()
        assertTrue(result is OnResult.Success)
        assertEquals(dummyHits.map { it.toDomain() }, (result as OnResult.Success).data)
    }

    @Test
    fun `getHitById returns the correct hit when available`() = runTest {
        val dummyHit = generateDummyHitEntity(objectId = "1")
        `when`(mockHitLocalDataSource.fetchHitById("1")).thenReturn(dummyHit)
        val result = hitRepository.getHitById("1")
        assertTrue(result is OnResult.Success)
        assertEquals(dummyHit.toDomain(), (result as OnResult.Success).data)
    }

    @Test
    fun `getHitById returns error when hit is not available`() = runTest {
        `when`(mockHitLocalDataSource.fetchHitById("1")).thenReturn(null)
        val result = hitRepository.getHitById("1")
        assertTrue(result is OnResult.Error)
    }

    @Test
    fun `deleteHitById deletes hit locally`() = runTest {
        val objectId = "1"
        hitRepository.deleteHitById(objectId)
        verify(mockHitLocalDataSource).deleteHit(objectId)
    }

    @Test
    fun `fetchAllHits fetches hits from local when available`() = runTest {
        val dummyHitsEntities = generateDummyHitEntityList()
        `when`(mockHitLocalDataSource.fetchHits()).thenReturn(dummyHitsEntities)
        val result = hitRepository.fetchAllHits()
        verify(mockHitLocalDataSource).fetchHits()
        assertTrue(result is OnResult.Success)
        assertEquals(dummyHitsEntities.map { it.toDomain() }, (result as OnResult.Success).data)
    }

    @Test
    fun `getHitById fetches hit details from local data`() = runTest {
        val dummyHitEntity = generateDummyHitEntity(objectId = "1", title = "Test Hit")
        `when`(mockHitLocalDataSource.fetchHitById("1")).thenReturn(dummyHitEntity)
        val result = hitRepository.getHitById("1")
        verify(mockHitLocalDataSource).fetchHitById("1")
        assertTrue(result is OnResult.Success)
        assertEquals(dummyHitEntity.toDomain(), (result as OnResult.Success).data)
    }

    @Test
    fun `deleteHitById deletes hit from local data`() = runTest {
        val objectId = "1"
        hitRepository.deleteHitById(objectId)
        verify(mockHitLocalDataSource).deleteHit(objectId)
    }

}