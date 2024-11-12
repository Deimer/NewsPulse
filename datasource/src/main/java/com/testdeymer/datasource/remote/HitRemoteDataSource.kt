package com.testdeymer.datasource.remote

import com.testdeymer.network.api.ApiService
import javax.inject.Inject

class HitRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
): IHitRemoteDataSource {

    override suspend fun getHits() =
        apiService.searchByDate().results
}