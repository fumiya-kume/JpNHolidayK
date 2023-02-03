import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

object JpNHoliday {

    fun isReady() :Boolean{
        val file = javaClass.getResource("syukujitsu.csv")
        val csvReader = csvReader().readAll(file.readText())
        return csvReader.isNotEmpty()
    }

    fun isHoliday(year: Int, month:Int, day:Int){

    }
}