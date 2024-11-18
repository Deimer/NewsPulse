package com.testdeymer.repository.utils

import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_IN
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_MINI
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_OUT
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_OUT_SHORT
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_FORMAT_SHORT
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_DATE_UNKNOWN
import com.testdeymer.repository.RepositoryConstants.Tags.TAG_TODAY
import java.text.SimpleDateFormat
import java.util.Calendar
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

fun Long.toShortHumanDate(): String {
    val calendarInput = Calendar.getInstance().apply { timeInMillis = this@toShortHumanDate }
    val calendarToday = Calendar.getInstance()
    return when {
        calendarToday.get(Calendar.YEAR) == calendarInput.get(Calendar.YEAR) &&
                calendarToday.get(Calendar.DAY_OF_YEAR) == calendarInput.get(Calendar.DAY_OF_YEAR) -> TAG_TODAY
        calendarInput.after(Calendar.getInstance().apply { add(Calendar.DATE, -7) }) ->
            SimpleDateFormat(TAG_DATE_FORMAT_MINI, Locale.getDefault())
                .format(calendarInput.time)
                .replaceFirstChar { it.uppercaseChar() }
        else ->
            SimpleDateFormat(TAG_DATE_FORMAT_SHORT, Locale.getDefault())
                .format(calendarInput.time)
                .replaceFirstChar { it.uppercaseChar() }
    }
}