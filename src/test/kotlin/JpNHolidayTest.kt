import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertTrue

class JpNHolidayTest {
    @Test
    fun `load csv file`(){
        assertTrue(JpNHoliday.isReady())
    }
}