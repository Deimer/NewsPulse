package com.testdeymer.repository.repositories

import com.testdeymer.repository.domain.HitDomain
import kotlinx.coroutines.flow.Flow

interface IHitRepository {

    fun getHits(): Flow<Result<List<HitDomain>>>

    fun fetchAllHits(): Flow<Result<List<HitDomain>>>

    fun getHitById(
        objectId: String
    ): Flow<Result<HitDomain>>

    fun deleteHitById(
        objectId: String
    ): Flow<Result<Unit>>
}