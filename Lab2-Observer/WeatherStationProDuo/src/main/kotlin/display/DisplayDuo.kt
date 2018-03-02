package display

import observer.IObserver
import WeatherStation
import WeatherStationPro
import info.WeatherInfo
import observer.IObservable

class DisplayDuo<in TInfo : WeatherInfo>(
        private val mHouseStation: WeatherStation,
        private val mStreetStation: WeatherStationPro
) : IObserver<TInfo> {

    override fun update(subject: IObservable<TInfo>) {
        val notificationSource = when (subject) {
            mStreetStation -> mStreetStation
            mHouseStation -> mHouseStation
            else -> return
        }

        println("""
            Information from ${notificationSource.javaClass.name}:
            ${subject.data}
            ----------------
        """.trimIndent())
    }
}
