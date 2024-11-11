package com.testdeymer.network.constants

object NetworkConstants {

    object ValuesKey {
        const val BASE_URL="https://api.themoviedb.org/3/"
    }

    object Defaults {
        const val MESSAGE_ERROR_GENERAL = "An error occurred!"
        const val STATUS_ERROR_GENERAL = false
        const val DEFAULT_TIMEOUT = 10L
        const val DEFAULT_TIMESTAMP = "1000"
    }

    object Url {
        const val SEARCH_BY_DATE = "search_by_date"
    }

    object Queries {
        const val QUERY = "query"
    }

    object Tags {
        const val TAG_MOBILE = "mobile"
    }
}