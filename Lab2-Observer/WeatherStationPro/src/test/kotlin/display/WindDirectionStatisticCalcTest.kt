package display

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class WindDirectionStatisticCalcTest {

    private lateinit var mCalc: WindDisplay.WindDirectionStatisticCalc

    @Before
    fun setup() {
        mCalc = WindDisplay.WindDirectionStatisticCalc()
    }

    @Test
    fun calcDirectionIfTheyDoNotChange() {
        repeat(1000) {
            mCalc.calc(45.0)
        }

        assertEquals("45.0", mCalc.toString())
    }

    @Test
    fun calcDirectionIfTheyChange() {
        mCalc.calc(0.0)
        mCalc.calc(180.0)

        assertEquals("90.0", mCalc.toString())
    }
}
