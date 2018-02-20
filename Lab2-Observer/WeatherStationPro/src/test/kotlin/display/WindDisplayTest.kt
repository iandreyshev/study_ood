package display

import info.WeatherInfo
import org.junit.Test

class WindDisplayTest {
    @Test
    fun calcWind() {
        val display = WindDisplay()

        display.update(WeatherInfo(
                windDirectionAngle = 45.0
        ))

        display.update(WeatherInfo(
                windDirectionAngle = 135.0
        ))
    }
}