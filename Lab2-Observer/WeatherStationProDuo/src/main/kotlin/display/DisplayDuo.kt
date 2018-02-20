package display

import info.WeatherInfoWithWind
import observer.IObserver
import WeatherStation
import WeatherStationPro
import observer.IObservable

class DisplayDuo(
        private val mStreetStation: WeatherStationPro,
        private val mHouseStation: WeatherStation) : IObserver<WeatherInfoWithWind> {

    override fun update(subject: IObservable<WeatherInfoWithWind>) {
        val notificationSource = if (subject == mStreetStation) mStreetStation else mHouseStation

        println("""
            Information from ${notificationSource.javaClass.name}:
            ${subject.data}
            ----------------
        """.trimIndent())
    }
}
