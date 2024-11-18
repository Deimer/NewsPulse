package com.testdeymer.database.manager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.testdeymer.database.constants.DataConstants.DATABASE_VERSION
import com.testdeymer.database.dao.HitDao
import com.testdeymer.database.entities.DeletedHitEntity
import com.testdeymer.database.entities.HitEntity

@Database(
    version = DATABASE_VERSION,
    exportSchema = false,
    entities = [
        HitEntity::class,
        DeletedHitEntity::class,
    ]
)
abstract class NewsPulseDatabase: RoomDatabase() {

    abstract fun getHitDao(): HitDao
}