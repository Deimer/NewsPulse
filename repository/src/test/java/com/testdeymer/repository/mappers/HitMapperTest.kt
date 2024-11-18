package com.testdeymer.repository.mappers

import com.testdeymer.database.entities.HitEntity
import com.testdeymer.network.dto.HitDTO
import com.testdeymer.repository.utils.toHumanDate
import com.testdeymer.repository.utils.toShortHumanDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class HitMapperTest {

    @Test
    fun `test HitEntity toDomain maps correctly`() {
        val hitEntity = HitEntity(
            objectId = "123",
            author = "Test Author",
            commentText = "This is a test comment",
            createdAt = "2024-11-12T10:00:00Z",
            updatedAt = "2024-11-13T12:00:00Z",
            createdAtTimestamp = 1699773600000L,
            title = "Test Title",
            url = "https://testurl.com"
        )
        val hitDomain = hitEntity.toDomain()
        assertEquals(hitEntity.objectId, hitDomain.objectId)
        assertEquals(hitEntity.author, hitDomain.author)
        assertEquals(hitEntity.commentText.orEmpty(), hitDomain.commentText)
        assertEquals(hitEntity.createdAtTimestamp.toShortHumanDate(), hitDomain.createdAtMini)
        assertEquals(hitEntity.createdAt.toShortHumanDate(), hitDomain.createdAtShort)
        assertEquals(hitEntity.createdAt.toHumanDate(), hitDomain.createdAtFull)
        assertEquals(hitEntity.updatedAt.toShortHumanDate(), hitDomain.updatedAtShort)
        assertEquals(hitEntity.updatedAt.toHumanDate(), hitDomain.updatedAtFull)
        assertEquals(hitEntity.title.orEmpty(), hitDomain.title)
        assertEquals(hitEntity.url.orEmpty(), hitDomain.url)
    }

    @Test
    fun `test HitDTO toEntity maps correctly`() {
        val hitDTO = HitDTO(
            objectID = "123",
            author = "Test Author",
            commentText = "This is a test comment",
            createdAt = "2024-11-12T10:00:00Z",
            createdAtI = 1699773600000L,
            storyTitle = "Test Title",
            storyUrl = "https://testurl.com",
            updatedAt = "2024-11-13T12:00:00Z",
            tags = listOf("tag1", "tag2"),
            highlightResult = null,
            parentId = null,
            storyId = null
        )
        val hitEntity = hitDTO.toEntity()
        assertEquals(hitDTO.objectID.orEmpty(), hitEntity.objectId)
        assertEquals(hitDTO.author.orEmpty(), hitEntity.author)
        assertEquals(hitDTO.commentText.orEmpty(), hitEntity.commentText)
        assertEquals(hitDTO.createdAt.orEmpty(), hitEntity.createdAt)
        assertEquals(hitDTO.createdAtI ?: 0L, hitEntity.createdAtTimestamp)
        assertEquals(hitDTO.storyTitle.orEmpty(), hitEntity.title)
        assertEquals(hitDTO.storyUrl.orEmpty(), hitEntity.url)
        assertEquals(hitDTO.updatedAt.orEmpty(), hitEntity.updatedAt)
    }
}