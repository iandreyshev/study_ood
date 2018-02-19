package display

import info.WeatherInfo
import observer.IObservable
import observer.IObserver

class StatsDisplay : IObserver<WeatherInfo> {

    private var mMinTemperature = Double.MIN_VALUE
    private var mMaxTemperature = Double.MAX_VALUE
    private var mAccTemperature: Double = .0
    private var mCountAcc = 0L

    override fun update(subject: IObservable<WeatherInfo>) {
        mMinTemperature = Math.min(mMinTemperature, subject.data.temperature)
        mMaxTemperature = Math.max(mMaxTemperature, subject.data.temperature)
        mAccTemperature += subject.data.temperature
        ++mCountAcc

        println("""
            Min temp $mMinTemperature
            Max temp $mMaxTemperature
            Average temp ${mAccTemperature / mCountAcc}
            ----------------
        """.trimIndent())
    }
}
