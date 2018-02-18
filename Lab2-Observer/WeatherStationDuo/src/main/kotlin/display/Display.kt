package display

import info.WeatherInfo
import observer.IObserver
import observer.ISubject
import weatherStation.WeatherStation

class Display(
        private val mStreetStation: WeatherStation,
        private val mHouseStation: WeatherStation) : IObserver<WeatherInfo> {

    override fun update(subject: ISubject<WeatherInfo>) {
        val notificationFrom = if (subject == mStreetStation) mStreetStation else mHouseStation

        println("""
            Information from ${notificationFrom.javaClass.name}:
            Current temp ${subject.data.temperature}
            Current Hum ${subject.data.humidity}
            Current Pressure ${subject.data.pressure}
            ----------------
        """.trimIndent())
    }
}
