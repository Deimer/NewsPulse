package com.testdeymer.network.dto

import com.google.gson.annotations.SerializedName

data class BaseResponseDTO<T>(
    @SerializedName("exhaustive")
    val exhaustive: ExhaustiveDTO?,
    @SerializedName("exhaustiveNbHits")
    val exhaustiveNbHits: String?,
    @SerializedName("exhaustiveTypo")
    val exhaustiveTypo: Boolean?,
    @SerializedName("hits")
    val results: T
)

data class ExhaustiveDTO(
    val nbHits: Boolean,
    val typo: Boolean
)