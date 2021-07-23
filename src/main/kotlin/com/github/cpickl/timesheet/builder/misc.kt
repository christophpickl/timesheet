package com.github.cpickl.timesheet.builder

import com.github.cpickl.timesheet.OffTag
import com.github.cpickl.timesheet.Tag
import java.time.LocalDate
import java.time.LocalTime

/** Construction of model because of invalid DSL definition failed. */
class BuilderException(message: String) : Exception(message)

interface IntermediateEntryDsoFields {
    val day: LocalDate
}

internal sealed class IntermediateEntryDso : IntermediateEntryDsoFields

internal data class IntermediateWorkDayEntryDso(
    override val day: LocalDate,
    val timeRange: Pair<LocalTime, LocalTime>,
    val about: String,
) : IntermediateEntryDso() {
    init {
        if(about.isBlank()) throw BuilderException("An entry's about text must not be blank for entry ${day.toParsableDate()}!")
    }
    var tag: TagDso = TagDso.none
}

internal data class DayOffEntryDso(
    override val day: LocalDate,
) : IntermediateEntryDso() {
    var reason: DayOffReasonDso? = null
}

enum class TagDso(val realTag: Tag) {
    none(Tag.None),
    biz(Tag.Business),
    orga(Tag.Organization),
    meet(Tag.Meeting),
    code(Tag.Coding),
    edu(Tag.Education),
    scrum(Tag.Scrum),
    ;

    companion object
}


enum class DayOffReasonDso(val realTag: OffTag) {
    Sickness(OffTag.Sick),
    PublicHoliday(OffTag.PublicHoliday),
    Vacation(OffTag.Vacation);

    companion object
}
