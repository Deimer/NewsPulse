package com.testdeymer.usecase.data

import com.testdeymer.repository.domain.HitDomain

fun generateDummyHitDomainList(): List<HitDomain> {
    return listOf(
        HitDomain(
            objectId = "1",
            author = "Author 1",
            commentText = "Comment text 1",
            createdAtMini = "Today",
            createdAtShort = "11 Nov",
            createdAtFull = "11 November 2024",
            updatedAtShort = "10 Nov",
            updatedAtFull = "10 November 2024",
            title = "Title 1",
            url = "https://example.com/1"
        ),
        HitDomain(
            objectId = "2",
            author = "Author 2",
            commentText = "Comment text 2",
            createdAtMini = "Yesterday",
            createdAtShort = "10 Nov",
            createdAtFull = "10 November 2024",
            updatedAtShort = "9 Nov",
            updatedAtFull = "9 November 2024",
            title = "Title 2",
            url = "https://example.com/2"
        ),
        HitDomain(
            objectId = "3",
            author = "Author 3",
            commentText = "Comment text 3",
            createdAtMini = "Last Week",
            createdAtShort = "4 Nov",
            createdAtFull = "4 November 2024",
            updatedAtShort = "3 Nov",
            updatedAtFull = "3 November 2024",
            title = "Title 3",
            url = "https://example.com/3"
        )
    )
}
