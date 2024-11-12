package com.testdeymer.repository.mappers

import com.testdeymer.database.entities.HitEntity
import com.testdeymer.network.dto.HitDTO
import com.testdeymer.repository.domain.HitDomain
import com.testdeymer.repository.utils.toHumanDate
import com.testdeymer.repository.utils.toShortHumanDate

fun HitEntity.toDomain(): HitDomain {
    return HitDomain(
        objectId = this.objectId,
        author = this.author,
        commentText = this.commentText.orEmpty(),
        createdAtShort = this.createdAt.toShortHumanDate(),
        createdAtFull = this.createdAt.toHumanDate(),
        updatedAtShort = this.updatedAt.toShortHumanDate(),
        updatedAtFull = this.updatedAt.toHumanDate(),
        title = this.title.orEmpty(),
        url = this.url.orEmpty()
    )
}

fun HitDTO.toEntity(): HitEntity {
    return HitEntity(
        objectId = this.objectID.orEmpty(),
        author = this.author.orEmpty(),
        commentText = this.commentText.orEmpty(),
        createdAt = this.createdAt.orEmpty(),
        createdAtTimestamp = this.createdAtI ?: 0L,
        title = this.storyTitle.orEmpty(),
        url = this.storyUrl.orEmpty(),
        updatedAt = this.updatedAt.orEmpty()
    )
}