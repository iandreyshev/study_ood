import info.WeatherInfo
import info.WeatherInfoWithWind
import observer.Observable

class WeatherStation : Observable<WeatherInfo>() {

    override val data: WeatherInfo
        get() = WeatherInfo(
                temperature = .0,
                humidity = .0,
                pressure = .0
        )

    fun setMeasurements(
            temperature: Double = data.temperature,
            humidity: Double = data.humidity,
            pressure: Double = data.pressure) {

        notifyObservers()
    }
}
