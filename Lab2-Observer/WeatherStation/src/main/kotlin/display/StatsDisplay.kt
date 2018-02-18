package display

import info.WeatherInfo
import observer.IObserver

class StatsDisplay : IObserver<WeatherInfo> {

    private val mTemperatureCalc = InfoCalc("temperature")
    private val mPressureCalc = InfoCalc("pressure")
    private val mHumidityCalc = InfoCalc("humidity")

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

    class InfoCalc(private val mName: String = "Value") {
        private var mMinValue: Double = Double.MIN_VALUE
        private var mMaxValue: Double = Double.MAX_VALUE
        private var mAccValue: Double = .0
        private var mCountAcc: Long = 0L

        fun calc(newValue: Double) {
            mMinValue = Math.min(mMinValue, newValue)
            mMaxValue = Math.max(mMaxValue, newValue)
            mAccValue += newValue
            ++mCountAcc
        }

        override fun toString(): String {
            return """
            Min $mName $mMinValue
            Max $mName $mMaxValue
            Average $mName ${mAccValue / mCountAcc}"""
        }
    }
}
