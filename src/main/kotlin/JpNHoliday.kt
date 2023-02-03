//import jdk.internal.loader.Resource
//import jdk.jpackage.internal.resources.ResourceLocator
//import java.io.File
//import java.util.ResourceBundle

object JpNHoliday {

    fun isReady() :Boolean{
        val file = javaClass.getResource("syukujitsu.csv")
        return file.readText().isNotBlank()
    }

    fun isHoliday(year: Int, month:Int, day:Int){

    }
}