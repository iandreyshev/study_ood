package display

import info.WeatherInfo
import org.junit.Assert.*
import org.junit.Test

class WindDisplayTest {
    @Test
    fun calcWind() {
        val display = WindDisplay()

        display.update(WeatherInfo(
                windDirectionAngle = 90.0
        ))
    }
}