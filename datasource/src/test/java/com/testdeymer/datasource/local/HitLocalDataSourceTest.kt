package com.testdeymer.datasource.local

import com.testdeymer.database.dao.HitDao
import com.testdeymer.datasource.data.generateDummyHitEntity
import com.testdeymer.datasource.data.generateDummyHitEntityList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class HitLocalDataSourceTest {

    @Mock
    private lateinit var mockHitDao: HitDao

    private lateinit var hitLocalDataSource: HitLocalDataSource

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        hitLocalDataSource = HitLocalDataSource(mockHitDao)
    }

    @Test
    fun `insertHit filters out deleted hits and inserts the rest`() = runTest {
        val deletedIds = listOf("1", "2")
        val hits = listOf(
            generateDummyHitEntity(objectId = "1", title = "Deleted Hit"),
            generateDummyHitEntity(objectId = "3", title = "Active Hit")
        )
        val expectedHitsToInsert = listOf(
            generateDummyHitEntity(objectId = "3", title = "Active Hit")
        )
        `when`(mockHitDao.getDeletedIds()).thenReturn(deletedIds)
        hitLocalDataSource.insertHit(hits)
        verify(mockHitDao).insertAll(expectedHitsToInsert)
    }

    @Test
    fun `fetchHitById returns correct hit details`() = runTest {
        val objectId = "3"
        val expectedHit = generateDummyHitEntity(objectId = objectId, title = "Test Hit")
        `when`(mockHitDao.getHitDetailsById(objectId)).thenReturn(expectedHit)
        val result = hitLocalDataSource.fetchHitById(objectId)
        assertEquals(expectedHit, result)
    }

    @Test
    fun `deleteHit marks the hit as deleted and removes it`() = runTest {
        val objectId = "5"
        hitLocalDataSource.deleteHit(objectId)
        verify(mockHitDao).deleteHitAndMarkAsDeleted(objectId)
    }

    @Test
    fun `fetchHits returns all hits`() = runTest {
        val hits = generateDummyHitEntityList(2)
        `when`(mockHitDao.getAll()).thenReturn(hits)
        val result = hitLocalDataSource.fetchHits()
        assertEquals(hits, result)
    }

    @Test
    fun `insertHit does not insert hits if all are deleted`() = runTest {
        val deletedIds = listOf("1", "2", "3")
        val hits = listOf(
            generateDummyHitEntity(objectId = "1", title = "Deleted Hit 1"),
            generateDummyHitEntity(objectId = "2", title = "Deleted Hit 2")
        )
        `when`(mockHitDao.getDeletedIds()).thenReturn(deletedIds)
        hitLocalDataSource.insertHit(hits)
        verify(mockHitDao, never()).insertAll(any())
    }

    @Test
    fun `fetchHitById returns null for non-existent objectId`() = runTest {
        val objectId = "999"
        `when`(mockHitDao.getHitDetailsById(objectId)).thenReturn(null)
        val result = hitLocalDataSource.fetchHitById(objectId)
        assertNull(result)
    }

    @Test
    fun `fetchHits returns empty list when no hits exist`() = runTest {
        `when`(mockHitDao.getAll()).thenReturn(emptyList())
        val result = hitLocalDataSource.fetchHits()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `deleteHit handles non-existent hit gracefully`() = runTest {
        val objectId = "999"
        hitLocalDataSource.deleteHit(objectId)
        verify(mockHitDao).deleteHitAndMarkAsDeleted(objectId)
    }
}