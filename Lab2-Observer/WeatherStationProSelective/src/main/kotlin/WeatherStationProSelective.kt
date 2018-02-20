import info.WeatherInfo
import observer.Observable

class WeatherStationProSelective : Observable<WeatherInfo>() {

    override val data: WeatherInfo
        get() = mMeasurements

    private var mMeasurements: WeatherInfo = WeatherInfo()

    fun setMeasurements(
            temperature: Double = mMeasurements.temperature,
            humidity: Double = mMeasurements.humidity,
            pressure: Double = mMeasurements.pressure,
            windDirection: Double = mMeasurements.windDirectionAngle,
            windSpeed: Double = mMeasurements.windSpeed) {

        mMeasurements = WeatherInfo(
                temperature = temperature,
                humidity = humidity,
                pressure = pressure,
                windDirectionAngle = windDirection,
                windSpeed = windSpeed
        )

        notifyObservers()
    }
}
