package display

import observer.IObserver
import WeatherStation
import WeatherStationPro
import info.WeatherInfo
import info.WeatherInfoWithWind
import observer.IObservable

class DisplayDuo(
        private val mHouseStation: WeatherStation,
        private val mStreetStation: WeatherStationPro) {

    private val mHouseDisplay = HouseDisplay()
    private val mStreetDisplay = StreetDisplay()

    init {
        mHouseStation.registerObserver(mHouseDisplay)
        mStreetStation.registerObserver(mStreetDisplay)
    }

    inner class HouseDisplay : IObserver<WeatherInfo> {
        override fun update(subject: IObservable<WeatherInfo>) {
            onUpdate(subject)
        }
    }

    inner class StreetDisplay : IObserver<WeatherInfoWithWind> {
        override fun update(subject: IObservable<WeatherInfoWithWind>) {
            onUpdate(subject)
        }
    }

    private fun onUpdate(subject: IObservable<*>) {
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
