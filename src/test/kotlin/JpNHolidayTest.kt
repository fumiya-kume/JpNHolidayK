import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class JpNHolidayTest {
    @Test
    fun `load csv file`() {
        assertTrue(JpNHoliday().isReady())
    }

    @Test
    fun `should 1955年1月1日 is holiday`() {
        assertTrue(JpNHoliday().isHoliday(year = 1955, month = 1, day = 1))
    }
}