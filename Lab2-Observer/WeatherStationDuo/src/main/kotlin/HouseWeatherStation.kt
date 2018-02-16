import info.WeatherInfo
import observer.Observable

class HouseWeatherStation : Observable<WeatherInfo>() {
    override val data: WeatherInfo
        get() = WeatherInfo()

    override val name: String
        get() = javaClass.name
}
