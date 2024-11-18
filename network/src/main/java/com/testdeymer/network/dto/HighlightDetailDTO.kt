package com.testdeymer.network.dto

import com.google.gson.annotations.SerializedName

data class HighlightDetailDTO(
    @SerializedName("fullyHighlighted")
    val fullyHighlighted: Boolean?,
    @SerializedName("matchLevel")
    val matchLevel: String?,
    @SerializedName("matchedWords")
    val matchedWords: List<String>?,
    @SerializedName("value")
    val value: String?
)