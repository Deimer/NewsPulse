package com.testdeymer.repository.repositories

import com.testdeymer.datasource.local.IHitLocalDataSource
import com.testdeymer.datasource.remote.IHitRemoteDataSource
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_NULL
import com.testdeymer.repository.mappers.toDomain
import com.testdeymer.repository.mappers.toEntity
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class HitRepository @Inject constructor(
    private val hitRemoteDataSource: IHitRemoteDataSource,
    private val hitLocalDataSource: IHitLocalDataSource
) : IHitRepository {

    override fun getHits() = flow {
        try {
            val dtoList = hitRemoteDataSource.getHits()
            hitLocalDataSource.insertHit(dtoList.map { it.toEntity() })
            val entities = hitLocalDataSource.fetchHits().map { it.toDomain() }
            emit(Result.success(entities))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    override fun fetchAllHits() = flow {
        try {
            val result = hitLocalDataSource.fetchHits()
            if(result.isNotEmpty()) {
                emit(Result.success(result.map { it.toDomain() }))
            } else {
                val dtoList = hitRemoteDataSource.getHits()
                val entities = dtoList.map { it.toEntity() }
                hitLocalDataSource.insertHit(entities)
                emit(Result.success(entities.map { it.toDomain() }))
            }
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    override fun getHitById(
        objectId: String
    ) = flow {
        try {
            hitLocalDataSource.fetchHitById(objectId)?.let { hitEntity ->
                emit(Result.success(hitEntity.toDomain()))
            } ?: emit(Result.failure(IllegalArgumentException(TAG_NULL)))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }

    override fun deleteHitById(
        objectId: String
    ) = flow {
        try {
            hitLocalDataSource.deleteHit(objectId)
            emit(Result.success(Unit))
        } catch (exception: Exception) {
            emit(Result.failure(exception))
        }
    }
}