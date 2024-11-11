package com.testdeymer.network.dto

data class HighlightDetailDTO(
    val fullyHighlighted: Boolean?,
    val matchLevel: String?,
    val matchedWords: List<String>?,
    val value: String?
)
