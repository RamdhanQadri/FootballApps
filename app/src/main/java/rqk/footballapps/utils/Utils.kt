package rqk.footballapps.utils

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun TextView.normal() {
    typeface = Typeface.DEFAULT
}

fun TextView.bold() {
    typeface = Typeface.DEFAULT_BOLD
}

fun String.formatDate(fromDateFormat: String = "dd/MM/yy",  toDateFormat: String = "E, dd MMM yyyy"): String? {
    val simpleDateFormat = SimpleDateFormat(fromDateFormat, Locale.getDefault())
    val dateFormatter = SimpleDateFormat(toDateFormat, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = simpleDateFormat.parse(this)
    dateFormatter.timeZone = TimeZone.getDefault()
    return dateFormatter.format(date)
}

fun String.formatTime(fromTimeFormat: String = "HH:mm", toTimeFormat: String = "HH:mm"): String? {
    val simpleDateFormat = SimpleDateFormat(fromTimeFormat, Locale.getDefault())
    val timeFormatter = SimpleDateFormat(toTimeFormat, Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val time = simpleDateFormat.parse(this)
    timeFormatter.timeZone = TimeZone.getDefault()
    return timeFormatter.format(time)
}

fun formatDateTime(date: String?, time: String?): Date? {
    val formatter = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("UTC")
    val dateTime = "$date $time"
    return formatter.parse(dateTime)
}

