package com.testdeymer.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.testdeymer.database.constants.DataConstants.Tables
import com.testdeymer.database.constants.DataConstants.Columns

@Entity(tableName = Tables.DELETED_HIT_TABLE)
data class DeletedHitEntity(
    @PrimaryKey
    @ColumnInfo(name = Columns.OBJECT_ID)
    val id: String,
)
