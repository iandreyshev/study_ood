package display

import info.WeatherInfo
import observer.IObserver
import WeatherStation
import observer.IObservable

class DuoDisplay(
        private val mStreetStation: WeatherStation,
        private val mHouseStation: WeatherStation) : IObserver<WeatherInfo> {

    override fun update(subject: IObservable<WeatherInfo>) {
        val notificationSource = if (subject == mStreetStation) mStreetStation else mHouseStation

        println("""
            Information from ${notificationSource.javaClass.name}:
            Current temp ${subject.data.temperature}
            Current Hum ${subject.data.humidity}
            Current Pressure ${subject.data.pressure}
            ----------------
        """.trimIndent())
    }
}
