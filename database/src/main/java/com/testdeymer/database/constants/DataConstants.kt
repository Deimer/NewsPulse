package com.testdeymer.database.constants

object DataConstants {

    const val DATABASE_VERSION = 1
    const val KEY_NAME_DATABASE = "news_pulse_database"

    object Tables {
        const val HIT_TABLE = "hit_table"
        const val DELETED_HIT_TABLE = "deleted_hit_table"
    }

    object Columns {
        const val OBJECT_ID = "objectId"
        const val AUTHOR = "author"
        const val COMMENT_TEXT = "commentText"
        const val CREATED_AT = "createdAt"
        const val CREATED_AT_TIMESTAMP = "createdAtTimestamp"
        const val TITLE = "title"
        const val URL = "url"
        const val UPDATED_AT = "updatedAt"
    }
}