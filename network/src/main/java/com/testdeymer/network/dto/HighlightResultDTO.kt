package com.testdeymer.network.dto

import com.google.gson.annotations.SerializedName

data class HighlightResultDTO(
    @SerializedName("author")
    val author: HighlightDetailDTO?,
    @SerializedName(value = "title", alternate= ["story_title"])
    val title: HighlightDetailDTO?,
    @SerializedName(value = "url", alternate= ["story_url"])
    val url: HighlightDetailDTO?,
    @SerializedName("comment_text")
    val commentText: HighlightDetailDTO?
)
