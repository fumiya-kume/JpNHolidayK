import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class JpNHolidayTest {

    private lateinit var jpNHoliday: JpNHoliday

    @BeforeEach
    fun setup() {
        jpNHoliday = JpNHoliday(HolidayRepository.getInstance())
    }

    @Test
    fun `load csv file`() {
        assertTrue(jpNHoliday.isReady())
    }

    @Test
    fun `should 1955年1月1日 is holiday`() {
        assertTrue(jpNHoliday.isHoliday(year = 1955, month = 1, day = 1))
    }
}
