import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

internal class HolidayRepositoryImpl : HolidayRepository {
    override fun getHolidayList(): List<Holiday> {
        val csvFile = HolidayRepositoryImpl::class.java.getResource(HOLIDAY_LIST_CSV_FILE)?.file
        return csvFile?.run {
            val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(csvFile), CHARSET_NAME))
            val text = bufferedReader.readText()
            val csvReader = csvReader().readAllWithHeader(text)
            csvReader.map {
                // YYYY/MM/DD
                val eventDateString = it[KEY_HOLIDAY]!!
                val eventDate = eventDateString.split('/')
                Holiday(
                    it[NAME_HOLIDAY],
                    eventDate[0].toIntOrNull(),
                    eventDate[1].toIntOrNull(),
                    eventDate[2].toIntOrNull()
                )
            }
        } ?: emptyList()
    }

    companion object {
        private const val HOLIDAY_LIST_CSV_FILE = "./syukujitsu.csv"
        private const val CHARSET_NAME = "Shift-JIS"
        private const val KEY_HOLIDAY = "国民の祝日・休日月日"
        private const val NAME_HOLIDAY = "国民の祝日・休日名称"
    }
}
