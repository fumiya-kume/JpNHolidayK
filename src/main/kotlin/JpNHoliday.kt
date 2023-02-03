import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import kotlinx.datetime.LocalDate
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader


object JpNHoliday {

    val file = javaClass.getResource("syukujitsu.csv")
    var b_reader = BufferedReader(InputStreamReader(FileInputStream(file.file), "Shift-JIS"))
    val text = b_reader.readText()
    val csvReader = csvReader().readAllWithHeader(text)

    fun isReady(): Boolean {
        return csvReader.isNotEmpty()
    }

    fun isHoliday(year: Int, month: Int, day: Int): Boolean {
        return csvReader.any {
            // YYYY/MM/DD
            val evetnDateString = it["国民の祝日・休日月日"]!!
            val eventDate = evetnDateString.split('/').run {
                LocalDate(year = this[0].toInt(), monthNumber = this[1].toInt(), dayOfMonth = this[2].toInt())
            }
            val target = LocalDate(year = year, monthNumber = month, dayOfMonth = day)
            return eventDate == target
        }
    }

    fun getNationalHolidayName(year: Int, month: Int, day: Int): String? {
        return csvReader.first() {
            // YYYY/MM/DD
            val evetnDateString = it["国民の祝日・休日月日"]!!
            val eventDate = evetnDateString.split('/').run {
                LocalDate(year = this[0].toInt(), monthNumber = this[1].toInt(), dayOfMonth = this[2].toInt())
            }
            val target = LocalDate(year = year, monthNumber = month, dayOfMonth = day)
             eventDate == target
        }[name]
    }

    private val name = "国民の祝日・休日名称"
}