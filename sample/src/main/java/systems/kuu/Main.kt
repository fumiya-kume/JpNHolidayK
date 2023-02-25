package systems.kuu

import HolidayRepository
import JpNHoliday

object Main {
    fun main() {
        println("Hello world!")
    }

    fun getNewYearHoliday(): String? {
        return JpNHoliday(HolidayRepository.getInstance()).getNationalHolidayName(1955, 1, 1)
    }
}
