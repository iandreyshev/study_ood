package display

import observer.IObserver
import WeatherStation
import WeatherStationPro
import info.WeatherInfo
import info.WeatherInfoWithWind
import observer.IObservable

class DisplayDuo(
        private val mOuterStation: WeatherStation,
        private val mInnerStation: WeatherStationPro) {

    private val mOuterDisplay = HouseDisplay()
    private val mInnerDisplay = StreetDisplay()

    init {
        mOuterStation.registerObserver(mOuterDisplay)
        mInnerStation.registerObserver(mInnerDisplay)
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
            mInnerStation -> mInnerStation
            mOuterStation -> mOuterStation
            else -> return
        }

        println("""
            Information from ${notificationSource.javaClass.name}:
            ${subject.data}
            ----------------
        """.trimIndent())
    }
}
