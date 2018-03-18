package display

import info.WeatherInfo
import observer.IObserver

class Display : IObserver<WeatherInfo> {
    override fun update(data: WeatherInfo) {
        println("""
            Current temp ${data.temperature}
            Current Hum ${data.humidity}
            Current Pressure ${data.pressure}
            ----------------
        """.trimIndent())
    }
}
