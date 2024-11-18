package com.testdeymer.network.api

import com.testdeymer.network.constants.NetworkConstants.Queries.QUERY
import com.testdeymer.network.constants.NetworkConstants.Tags.TAG_MOBILE
import com.testdeymer.network.constants.NetworkConstants.Url.SEARCH_BY_DATE
import com.testdeymer.network.dto.BaseResponseDTO
import com.testdeymer.network.dto.HitDTO
import retrofit2.http.Query
import retrofit2.http.GET

interface ApiService {

    @GET(SEARCH_BY_DATE)
    suspend fun searchByDate(
        @Query(QUERY) query: String = TAG_MOBILE
    ): BaseResponseDTO<List<HitDTO>>
}