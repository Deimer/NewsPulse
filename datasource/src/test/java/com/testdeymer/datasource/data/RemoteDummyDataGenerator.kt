package com.testdeymer.datasource.data

import com.testdeymer.network.dto.BaseResponseDTO
import com.testdeymer.network.dto.HighlightDetailDTO
import com.testdeymer.network.dto.HighlightResultDTO
import com.testdeymer.network.dto.HitDTO

fun generateDummyBaseResponseWithList(
    listResults: List<HitDTO> = emptyList()
) = BaseResponseDTO(
    exhaustive = null,
    exhaustiveNbHits = "",
    exhaustiveTypo = null,
    results = listResults
)

fun generateDummyBaseResponseDTO(): BaseResponseDTO<List<HitDTO>> {
    return BaseResponseDTO(
        exhaustive = null,
        exhaustiveNbHits = "",
        exhaustiveTypo = null,
        results = listOf(
            HitDTO(
                highlightResult = generateDummyHighlightResultDTO(),
                tags = listOf("tag1", "tag2"),
                author = "Author 1",
                commentText = "This is a dummy comment 1.",
                createdAt = "2024-11-12T12:00:00Z",
                createdAtI = 1699788000,
                objectID = "1",
                parentId = 101,
                storyId = 201,
                storyTitle = "Dummy Story Title 1",
                storyUrl = "https://example.com/story1",
                updatedAt = "2024-11-12T13:00:00Z"
            ),
            HitDTO(
                highlightResult = generateDummyHighlightResultDTO(),
                tags = listOf("tag3"),
                author = "Author 2",
                commentText = "This is a dummy comment 2.",
                createdAt = "2024-11-12T14:00:00Z",
                createdAtI = 1699791600,
                objectID = "2",
                parentId = 102,
                storyId = 202,
                storyTitle = "Dummy Story Title 2",
                storyUrl = "https://example.com/story2",
                updatedAt = "2024-11-12T15:00:00Z"
            )
        )
    )
}

fun generateDummyBaseResponseDTOWithPartialData(): BaseResponseDTO<List<HitDTO>> {
    return BaseResponseDTO(
        exhaustive = null,
        exhaustiveNbHits = "",
        exhaustiveTypo = null,
        results = listOf(
            HitDTO(
                highlightResult = null,
                tags = null,
                author = null,
                commentText = "Partial dummy comment 1.",
                createdAt = "2024-11-12T16:00:00Z",
                createdAtI = 1699795200,
                objectID = "3",
                parentId = null,
                storyId = null,
                storyTitle = null,
                storyUrl = null,
                updatedAt = null
            ),
            HitDTO(
                highlightResult = generateDummyHighlightResultDTO(),
                tags = listOf("tag4"),
                author = "Partial Author",
                commentText = null,
                createdAt = null,
                createdAtI = null,
                objectID = "4",
                parentId = null,
                storyId = null,
                storyTitle = "Partial Dummy Story",
                storyUrl = null,
                updatedAt = null
            )
        )
    )
}

fun generateDummyHighlightResultDTO(): HighlightResultDTO {
    return HighlightResultDTO(
        author = generateDummyHighlightDetailDTO("Dummy Highlight Author"),
        title = generateDummyHighlightDetailDTO("Dummy Highlight Title"),
        url = generateDummyHighlightDetailDTO("https://example.com/highlight-url"),
        commentText = generateDummyHighlightDetailDTO("Dummy Highlight Comment")
    )
}

fun generateDummyHighlightDetailDTO(value: String): HighlightDetailDTO {
    return HighlightDetailDTO(
        fullyHighlighted = false,
        matchLevel = "full",
        matchedWords = listOf("dummy", "highlight"),
        value = value
    )
}

