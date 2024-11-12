package com.testdeymer.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testdeymer.database.constants.DataConstants.Tables
import com.testdeymer.database.constants.DataConstants.Columns

@Entity(tableName = Tables.HIT_TABLE)
data class HitEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.OBJECT_ID)
    val objectId: String,
    @ColumnInfo(name = Columns.AUTHOR)
    val author: String,
    @ColumnInfo(name = Columns.COMMENT_TEXT)
    val commentText: String?,
    @ColumnInfo(name = Columns.TITLE)
    val title: String?,
    @ColumnInfo(name = Columns.URL)
    val url: String?,
    @ColumnInfo(name = Columns.CREATED_AT)
    val createdAt: String,
    @ColumnInfo(name = Columns.UPDATED_AT)
    val updatedAt: String,
    @ColumnInfo(name = Columns.CREATED_AT_TIMESTAMP)
    val createdAtTimestamp: Long,
)
