import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class JpNHolidayTest {
    @Test
    fun `load csv file`(){
        assertTrue(JpNHoliday.isReady())
    }
}