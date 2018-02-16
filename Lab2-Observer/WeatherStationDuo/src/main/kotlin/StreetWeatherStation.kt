import info.WeatherInfo
import observer.Observable

class StreetWeatherStation : Observable<WeatherInfo>() {

    override val data: WeatherInfo
        get() = WeatherInfo(
                temperature = mTemperature,
                humidity = mHumidity,
                pressure = mPressure
        )

    override val name: String
        get() = javaClass.name

    private var mTemperature = 0.0
    private var mHumidity = 0.0
    private var mPressure = 760.0

    fun SetMeasurements(temp: Double, humidity: Double, pressure: Double) {
        mTemperature = temp
        mHumidity = humidity
        mPressure = pressure

        notifyObservers()
    }
}
