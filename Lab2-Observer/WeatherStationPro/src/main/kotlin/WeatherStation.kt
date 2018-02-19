import info.WeatherInfo
import observer.Observable

class WeatherStation : Observable<WeatherInfo>() {

    override val data: WeatherInfo
        get() = mMeasurements

    private var mMeasurements: WeatherInfo = WeatherInfo()

    fun setMeasurements(
            temperature: Double = mMeasurements.temperature,
            humidity: Double = mMeasurements.humidity,
            pressure: Double = mMeasurements.pressure,
            windDirection: Int = mMeasurements.windDirection,
            windSpeed: Double = mMeasurements.windSpeed) {

        val newMeasurements = WeatherInfo(
                temperature = temperature,
                humidity = humidity,
                pressure = pressure,
                windDirection = windDirection,
                windSpeed = windSpeed
        )

        if (newMeasurements != mMeasurements) {
            notifyObservers()
        }
    }
}
