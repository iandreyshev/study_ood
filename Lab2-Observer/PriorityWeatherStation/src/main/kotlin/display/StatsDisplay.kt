package display

import info.WeatherInfo
import observer.IObserver

class StatsDisplay : IObserver<WeatherInfo> {

    private var mMinTemperature = Double.MIN_VALUE
    private var mMaxTemperature = Double.MAX_VALUE
    private var mAccTemperature: Double = .0
    private var mCountAcc = 0L

    override fun update(data: WeatherInfo) {
        mMinTemperature = Math.min(mMinTemperature, data.temperature)
        mMaxTemperature = Math.max(mMaxTemperature, data.temperature)
        mAccTemperature += data.temperature
        ++mCountAcc

        println("""
            Min temp $mMinTemperature
            Max temp $mMaxTemperature
            Average temp ${mAccTemperature / mCountAcc}
            ----------------
        """.trimIndent())
    }
}
