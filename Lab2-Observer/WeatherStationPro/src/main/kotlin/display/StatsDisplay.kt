package display

import info.WeatherInfo
import observer.IObserver

class StatsDisplay : IObserver<WeatherInfo> {

    private val mTemperatureCalc = StatisticCalc("temperature")
    private val mPressureCalc = StatisticCalc("pressure")
    private val mHumidityCalc = StatisticCalc("humidity")

    override fun update(data: WeatherInfo) {
        mTemperatureCalc.calc(data.temperature)
        mPressureCalc.calc(data.pressure)
        mHumidityCalc.calc(data.humidity)

        println("""
            $mTemperatureCalc
            $mPressureCalc
            $mHumidityCalc
            ----------------""".trimIndent())
    }
}
