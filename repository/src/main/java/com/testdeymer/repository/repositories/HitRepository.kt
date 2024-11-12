package com.testdeymer.repository.repositories

import com.testdeymer.datasource.local.IHitLocalDataSource
import com.testdeymer.datasource.remote.IHitRemoteDataSource
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_NULL
import com.testdeymer.repository.mappers.toDomain
import com.testdeymer.repository.mappers.toEntity
import com.testdeymer.repository.utils.OnResult
import java.lang.Exception
import javax.inject.Inject

class HitRepository @Inject constructor(
    private val hitRemoteDataSource: IHitRemoteDataSource,
    private val hitLocalDataSource: IHitLocalDataSource
) : IHitRepository {

    override suspend fun getAllHits() = try {
        val result = hitLocalDataSource.fetchHits()
        if(result.isNotEmpty()) {
            OnResult.Success(result.map { it.toDomain() })
        } else {
            val dtoList = hitRemoteDataSource.getHits()
            val entities = dtoList.map { it.toEntity() }
            hitLocalDataSource.insertHit(entities)
            OnResult.Success(entities.map { it.toDomain() })
        }
    } catch (exception: Exception) {
        OnResult.Error(exception)
    }

    override suspend fun getHitById(
        objectId: String
    ) = try {
         hitLocalDataSource.fetchHitById(objectId)?.let { hit ->
             OnResult.Success(hit.toDomain())
         } ?: run {
             OnResult.Error(IllegalArgumentException(TAG_NULL))
         }
    } catch (exception: Exception) {
        OnResult.Error(exception)
    }

    override suspend fun deleteHitById(
        objectId: String
    ) = hitLocalDataSource.deleteHit(
        objectId
    )
}