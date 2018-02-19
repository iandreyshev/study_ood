package display

import info.WeatherInfo
import observer.IObserver
import WeatherStation
import observer.IObservable

class Display(
        private val mStreetStation: WeatherStation,
        private val mHouseStation: WeatherStation) : IObserver<WeatherInfo> {

    override fun update(subject: IObservable<WeatherInfo>) {
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
