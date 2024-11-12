package com.testdeymer.datasource.local

import com.testdeymer.database.dao.HitDao
import com.testdeymer.database.entities.HitEntity
import javax.inject.Inject

class HitLocalDataSource @Inject constructor(
    private val hitDao: HitDao,
): IHitLocalDataSource {

    override suspend fun insertHit(
        hits: List<HitEntity>
    ) {
        val deletedIds = hitDao.getDeletedIds()
        val hitsToInsert = hits.filter { hit ->
            hit.objectId !in deletedIds
        }
        hitDao.insertAll(hitsToInsert)
    }

    override suspend fun fetchHitById(
        objectId: String
    ) = hitDao.getHitDetailsById(objectId)

    override suspend fun deleteHit(
        objectId: String
    ) = hitDao.deleteHitAndMarkAsDeleted(objectId)

    override suspend fun fetchHits() =
        hitDao.getAll()
}