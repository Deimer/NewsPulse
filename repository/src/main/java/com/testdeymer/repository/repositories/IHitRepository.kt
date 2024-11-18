package com.testdeymer.repository.repositories

import com.testdeymer.repository.domain.HitDomain
import com.testdeymer.repository.utils.OnResult

interface IHitRepository {

    suspend fun getHits(): OnResult<List<HitDomain>>

    suspend fun fetchAllHits(): OnResult<List<HitDomain>>

    suspend fun getHitById(
        objectId: String
    ): OnResult<HitDomain>

    suspend fun deleteHitById(
        objectId: String
    )
}