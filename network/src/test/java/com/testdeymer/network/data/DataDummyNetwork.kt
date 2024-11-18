package com.testdeymer.network.data

import com.testdeymer.network.dto.BaseResponseDTO
import com.testdeymer.network.dto.HitDTO

fun generateDummyBaseResponse(
    emptyResults: Boolean = false
) = BaseResponseDTO(
    exhaustive = null,
    exhaustiveNbHits = "",
    exhaustiveTypo = null,
    results = if(emptyResults) emptyList() else generateDummyHitListDTO()
)

fun generateDummyBaseResponseWithList(
    listResults: List<HitDTO> = emptyList()
) = BaseResponseDTO(
    exhaustive = null,
    exhaustiveNbHits = "",
    exhaustiveTypo = null,
    results = listResults
)

fun generateLargeDummyHitDTOList() = List(1000) { index ->
    HitDTO(
        highlightResult = null,
        tags = listOf("tag$index"),
        author = "Author$index",
        commentText = "Comment$index",
        createdAt = "2024-11-11T17:36:20Z",
        createdAtI = (1234567890 + index).toLong(),
        objectID = index.toString(),
        parentId = index.toLong(),
        storyId = (index + 1000).toLong(),
        storyTitle = "Story Title $index",
        storyUrl = "http://example.com/story$index",
        updatedAt = "2024-11-11T18:36:20Z"
    )
}

fun generateDummyHitListDTO(): List<HitDTO> {
    return listOf(
        HitDTO(
            highlightResult = null,
            tags = listOf("tag1", "tag2"),
            author = "Author1",
            commentText = "Comment1",
            createdAt = "2024-11-11T17:36:20Z",
            createdAtI = 1234567890,
            objectID = "1",
            parentId = 1,
            storyId = 101,
            storyTitle = "Story Title 1",
            storyUrl = "http://example.com/story1",
            updatedAt = "2024-11-11T18:36:20Z"
        ),
        HitDTO(
            highlightResult = null,
            tags = listOf("tag3"),
            author = "Author2",
            commentText = "Comment2",
            createdAt = "2024-11-11T18:36:20Z",
            createdAtI = 1234567891,
            objectID = "2",
            parentId = 2,
            storyId = 102,
            storyTitle = "Story Title 2",
            storyUrl = "http://example.com/story2",
            updatedAt = "2024-11-11T19:36:20Z"
        )
    )
}