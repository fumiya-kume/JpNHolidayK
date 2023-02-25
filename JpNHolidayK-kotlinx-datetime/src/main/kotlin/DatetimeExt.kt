import kotlinx.datetime.LocalDateTime

fun LocalDateTime.isHoliday(): Boolean {
    return JpNHoliday(HolidayRepository.getInstance()).isHoliday(year = year, month = month.value, day = dayOfMonth)
}

fun LocalDateTime.getNationalHolidayName(): String? {
    return JpNHoliday(HolidayRepository.getInstance()).getNationalHolidayName(
        year = year,
        month = month.value,
        day = dayOfMonth
    )
}