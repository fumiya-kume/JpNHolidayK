import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class HolidayRepositoryImpl : HolidayRepository {
    override fun getHolidayList(): List<Holiday> {
        val csvFile = javaClass.getResource("syukujitsu.csv")?.file
        return csvFile?.run {
            val bufferedReader = BufferedReader(InputStreamReader(FileInputStream(csvFile), "Shift-JIS"))
            val text = bufferedReader.readText()
            val csvReader = csvReader().readAllWithHeader(text)
            return csvReader.map {
                // YYYY/MM/DD
                val evetDateString = it["国民の祝日・休日月日"]!!
                val eventDate = evetDateString.split('/')
                Holiday(
                    it[name],
                    eventDate[0].toIntOrNull(),
                    eventDate[1].toIntOrNull(),
                    eventDate[2].toIntOrNull()
                )
            }
        } ?: emptyList()
    }

    companion object {
        private const val name = "国民の祝日・休日名称"
    }
}
