package com.testdeymer.repository.data

import com.testdeymer.database.entities.HitEntity
import com.testdeymer.network.dto.HighlightDetailDTO
import com.testdeymer.network.dto.HighlightResultDTO
import com.testdeymer.network.dto.HitDTO

fun generateDummyHitDTOList(): List<HitDTO> {
    return listOf(
        generateDummyHitDTO(objectID = "1", title = "Title 1"),
        generateDummyHitDTO(objectID = "2", title = "Title 2")
    )
}

fun generateDummyHitDTO(
    objectID: String,
    title: String? = null
): HitDTO {
    return HitDTO(
        highlightResult = generateDummyHighlightResultDTO(),
        tags = listOf("tag1", "tag2"),
        author = "Author $objectID",
        commentText = "This is a comment for $objectID.",
        createdAt = "2024-11-12T12:00:00Z",
        createdAtI = 1699788000,
        objectID = objectID,
        parentId = null,
        storyId = null,
        storyTitle = title,
        storyUrl = "https://example.com/story$objectID",
        updatedAt = null
    )
}

fun generateDummyHitEntityList(): List<HitEntity> {
    return listOf(
        generateDummyHitEntity(objectId = "1", title = "Title 1"),
        generateDummyHitEntity(objectId = "2", title = "Title 2")
    )
}

fun generateDummyHitEntity(
    objectId: String,
    title: String? = null
): HitEntity {
    return HitEntity(
        objectId = objectId,
        title = title,
        author = "Author $objectId",
        commentText = "This is a comment for $objectId.",
        url = "https://example.com/story$objectId",
        createdAt = "2024-11-12T12:00:00Z",
        updatedAt = "2024-11-12T13:00:00Z",
        createdAtTimestamp = 1699788000
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
