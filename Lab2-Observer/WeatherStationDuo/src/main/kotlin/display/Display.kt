package display

import info.WeatherInfo
import observer.IObserver
import observer.ISubject

class Display : IObserver<WeatherInfo> {
    override fun update(subject: ISubject<WeatherInfo>) {
        println("""
            Information from ${subject.name}:
            Current temp ${subject.data.temperature}
            Current Hum ${subject.data.humidity}
            Current Pressure ${subject.data.pressure}
            ----------------
        """.trimIndent())
    }
}
