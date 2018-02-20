package info

open class WeatherInfo(
        val temperature: Double = .0,
        val humidity: Double = .0,
        val pressure: Double = .0) {

    override fun toString(): String {
        return """
            temperature = $temperature
            humidity = $humidity
            pressure = $pressure
        """
    }
}
