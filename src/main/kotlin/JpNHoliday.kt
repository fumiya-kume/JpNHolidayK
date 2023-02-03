import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

object JpNHoliday {

    val file = javaClass.getResource("syukujitsu.csv")
    val csvReader = csvReader().readAll(file.readText())

    fun isReady() :Boolean{
        return csvReader.isNotEmpty()
    }

    fun isHoliday(year: Int, month:Int, day:Int){

    }
}