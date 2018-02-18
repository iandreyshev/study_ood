package weatherStation

import info.WeatherInfo
import observer.Observable

open class WeatherStation : Observable<WeatherInfo>() {

    private var mTemperature = 0.0
    private var mHumidity = 0.0
    private var mPressure = 760.0

    override val data: WeatherInfo
        get() = WeatherInfo(
                temperature = mTemperature,
                humidity = mHumidity,
                pressure = mPressure
        )

    fun SetMeasurements(temp: Double, humidity: Double, pressure: Double) {
        mTemperature = temp
        mHumidity = humidity
        mPressure = pressure

        notifyObservers()
    }
}
