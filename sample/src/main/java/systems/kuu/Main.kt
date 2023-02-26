package systems.kuu

import JpNHoliday

object Main {
    fun main() {
        println("Hello world!")
    }

    fun getNewYearHoliday(): String? {
        return JpNHoliday().getNationalHolidayName(1955, 1, 1)
    }
}
