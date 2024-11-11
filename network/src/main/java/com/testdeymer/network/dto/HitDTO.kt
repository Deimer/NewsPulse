package com.testdeymer.network.dto

import com.google.gson.annotations.SerializedName

data class HitDTO(
    @SerializedName("_highlightResult")
    val highlightResult: HighlightResultDTO?,
    @SerializedName("_tags")
    val tags: List<String>?,
    @SerializedName("author")
    val author: String?,
    @SerializedName("comment_text")
    val commentText: String?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("created_at_i")
    val createdAtI: Long?,
    @SerializedName("objectID")
    val objectID: String?,
    @SerializedName("parent_id")
    val parentId: Long?,
    @SerializedName("story_id")
    val storyId: Long?,
    @SerializedName("story_title")
    val storyTitle: String?,
    @SerializedName("story_url")
    val storyUrl: String?,
    @SerializedName("updated_at")
    val updatedAt: String?
)
