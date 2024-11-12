package com.testdeymer.repository.domain

data class HitDomain(
    val objectId: String,
    val author: String,
    val commentText: String,
    val createdAtShort: String,
    val createdAtFull: String,
    val updatedAtShort: String,
    val updatedAtFull: String,
    val title: String,
    val url: String,
)
