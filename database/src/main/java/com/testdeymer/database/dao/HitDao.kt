package com.testdeymer.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.testdeymer.database.constants.DataConstants.Tables.DELETED_HIT_TABLE
import com.testdeymer.database.constants.DataConstants.Tables.HIT_TABLE
import com.testdeymer.database.entities.DeletedHitEntity
import com.testdeymer.database.entities.HitEntity

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(hits: List<HitEntity>)

    @Query("""
        SELECT * FROM $HIT_TABLE 
        WHERE objectId NOT IN (SELECT objectId FROM $DELETED_HIT_TABLE)
        ORDER BY createdAtTimestamp DESC
    """)
    suspend fun getAll(): List<HitEntity>

    @Query("SELECT objectId FROM $DELETED_HIT_TABLE")
    suspend fun getDeletedIds(): List<String>

    @Query("DELETE FROM $HIT_TABLE WHERE objectId = :objectId")
    suspend fun deleteHit(objectId: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun markAsDeleted(deletedHit: DeletedHitEntity)

    @Query("SELECT * FROM $HIT_TABLE WHERE objectId = :id LIMIT 1")
    suspend fun getHitDetailsById(id: String): HitEntity

    @Transaction
    suspend fun deleteHitAndMarkAsDeleted(objectId: String) {
        deleteHit(objectId)
        markAsDeleted(DeletedHitEntity(objectId))
    }
}