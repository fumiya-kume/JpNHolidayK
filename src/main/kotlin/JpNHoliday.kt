class JpNHoliday(
    private val holidayRepository: HolidayRepository
) {

    fun isReady(): Boolean {
        return holidayRepository.getHolidayList().isNotEmpty()
    }

    fun isHoliday(year: Int, month: Int, day: Int): Boolean {
        return holidayRepository.getHolidayList().any {
            year == it.year && month == it.month && day == it.day
        }
    }

    fun getNationalHolidayName(year: Int, month: Int, day: Int): String? {
        return holidayRepository.getHolidayList().firstOrNull {
            year == it.year && month == it.month && day == it.day
        }?.name
    }
}
