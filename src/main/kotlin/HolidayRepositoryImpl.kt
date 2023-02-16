import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class HolidayRepositoryImpl : HolidayRepository {
    override fun getHolidayList(): List<Holiday> {
        val file = javaClass.getResource("syukujitsu.csv")
        val b_reader = BufferedReader(InputStreamReader(FileInputStream(file.file), "Shift-JIS"))
        val text = b_reader.readText()
        val csvReader = csvReader().readAllWithHeader(text)

        return csvReader.map {
            // YYYY/MM/DD
            val evetDateString = it["国民の祝日・休日月日"]!!
            val eventDate = evetDateString.split('/')
            Holiday(
                it[name],
                eventDate[0].toIntOrNull(),
                eventDate[1].toIntOrNull(),
                eventDate[2].toIntOrNull(),
            )
        }
    }

    companion object{
        private const val name = "国民の祝日・休日名称"
    }
}