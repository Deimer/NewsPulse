package com.testdeymer.datasource.local

import com.testdeymer.database.entities.HitEntity

interface IHitLocalDataSource {

    suspend fun insertHit(
        hits: List<HitEntity>
    )

    suspend fun fetchHitById(
        objectId: String
    ): HitEntity

    suspend fun deleteHit(objectId: String)

    suspend fun fetchHits(): List<HitEntity>
}