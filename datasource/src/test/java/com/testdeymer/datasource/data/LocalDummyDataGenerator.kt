package com.testdeymer.datasource.data

import com.testdeymer.database.entities.HitEntity

fun generateDummyHitEntity(
    objectId: String = "1",
    title: String? = "Sample Title",
    author: String = "Sample Author",
    commentText: String? = "This is a sample comment.",
    url: String? = "https://sampleurl.com",
    createdAt: String = "2024-11-12T10:00:00Z",
    updatedAt: String = "2024-11-12T12:00:00Z",
    createdAtTimestamp: Long = System.currentTimeMillis()
): HitEntity {
    return HitEntity(
        objectId = objectId,
        title = title,
        author = author,
        commentText = commentText,
        url = url,
        createdAt = createdAt,
        updatedAt = updatedAt,
        createdAtTimestamp = createdAtTimestamp
    )
}

fun generateDummyHitEntityList(
    size: Int = 5,
    startId: Int = 1
): List<HitEntity> {
    return List(size) { index ->
        generateDummyHitEntity(
            objectId = (startId + index).toString(),
            title = "Sample Title ${startId + index}",
            author = "Author ${startId + index}",
            commentText = "This is a comment for item ${startId + index}.",
            url = "https://sampleurl${startId + index}.com",
            createdAt = "2024-11-${startId + index}T10:00:00Z",
            updatedAt = "2024-11-${startId + index}T12:00:00Z",
            createdAtTimestamp = System.currentTimeMillis() + (index * 1000L)
        )
    }
}
