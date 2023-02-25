class JpNHoliday(
    private val holidayRepository: HolidayRepository = HolidayRepository.getInstance()
) {

    fun getHolidayList(): List<Holiday> {
        return holidayRepository.getHolidayList()
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
