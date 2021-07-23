package com.github.cpickl.timesheet.builder

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val DATE_FORMAT = DateTimeFormatter.ofPattern("d.M.yy")

fun LocalDate.toParsableDate() = "$dayOfMonth.$monthValue.${year.toString().substring(2)}"

fun String.parseDate(): LocalDate = LocalDate.parse(this, DATE_FORMAT)

fun String.parseTime(): Pair<LocalTime, LocalTime> {
    val parts = split("-")
    return parseTimePart(parts[0]) to parseTimePart(parts[1])
}

private fun parseTimePart(part: String) = if (part.contains(":")) {
    val hourMinute = part.split(":")
    LocalTime.of(hourMinute[0].toInt(), hourMinute[1].toInt())
} else {
    LocalTime.of(part.toInt(), 0)
}
