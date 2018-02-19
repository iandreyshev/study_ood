package display

import info.WeatherInfo
import observer.IObserver

class WindDisplay : IObserver<WeatherInfo> {
    override fun update(data: WeatherInfo) {

    }
}
