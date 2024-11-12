package com.testdeymer.repository.utils

import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_IN
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_OUT
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_OUT_SHORT
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_UNKNOWN
import java.text.SimpleDateFormat
import java.util.Locale

fun String?.toHumanDate(): String {
    return this?.let {
        val formatIn = SimpleDateFormat(TAG_DATE_FORMAT_IN, Locale.US)
        val formatOut = SimpleDateFormat(TAG_DATE_FORMAT_OUT, Locale.US)
        val date = formatIn.parse(this)
        date?.let {
            formatOut.format(date)
        } ?: TAG_DATE_UNKNOWN
    } ?: TAG_DATE_UNKNOWN
}

fun String?.toShortHumanDate(): String {
    return this?.let {
        val formatIn = SimpleDateFormat(TAG_DATE_FORMAT_IN, Locale.US)
        val formatOut = SimpleDateFormat(TAG_DATE_FORMAT_OUT_SHORT, Locale.US)
        val date = formatIn.parse(this)
        date?.let {
            formatOut.format(date)
        } ?: TAG_DATE_UNKNOWN
    } ?: TAG_DATE_UNKNOWN
}