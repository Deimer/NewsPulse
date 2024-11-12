package com.testdeymer.datasource.remote

import com.testdeymer.network.dto.HitDTO

interface IHitRemoteDataSource {

    suspend fun getHits(): List<HitDTO>
}