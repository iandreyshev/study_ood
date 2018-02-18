package display

import info.WeatherInfo
import org.junit.Assert.*
import org.junit.Test

class StatsDisplayTest {
    @Test
    fun printWeatherStatistic() {
        val display = StatsDisplay()
        val info = WeatherInfo(
                14.5,
                10.125,
                .100)

        display.update(info)
    }
}