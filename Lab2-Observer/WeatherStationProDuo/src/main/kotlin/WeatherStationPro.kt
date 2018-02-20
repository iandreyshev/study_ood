import info.WeatherInfoWithWind
import observer.Observable

class WeatherStationPro : Observable<WeatherInfoWithWind>() {

    override val data: WeatherInfoWithWind
        get() = mMeasurements

    private var mMeasurements: WeatherInfoWithWind = WeatherInfoWithWind(
            temperature = .0,
            humidity = .0,
            pressure = .0,
            windDirectionAngle = .0,
            windSpeed = .0
    )

    fun setMeasurements(
            temperature: Double = mMeasurements.temperature,
            humidity: Double = mMeasurements.humidity,
            pressure: Double = mMeasurements.pressure,
            windDirection: Double = mMeasurements.windDirectionAngle,
            windSpeed: Double = mMeasurements.windSpeed) {

        mMeasurements = WeatherInfoWithWind(
                temperature = temperature,
                humidity = humidity,
                pressure = pressure,
                windDirectionAngle = windDirection,
                windSpeed = windSpeed
        )

        notifyObservers()
    }
}
