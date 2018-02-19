import info.WeatherInfo
import observer.PriorityObservable

class WeatherStation : PriorityObservable<WeatherInfo>() {

    private var mTemperature = 0.0
    private var mHumidity = 0.0
    private var mPressure = 760.0

    override val data: WeatherInfo
        get() = WeatherInfo(
                temperature = mTemperature,
                humidity = mHumidity,
                pressure = mPressure
        )

    fun setMeasurements(temp: Double, humidity: Double, pressure: Double) {
        mTemperature = temp
        mHumidity = humidity
        mPressure = pressure

        notifyObservers()
    }
}
