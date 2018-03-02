package info

class WeatherInfoWithWind(
        val temperature: Double = .0,
        val humidity: Double = .0,
        val pressure: Double = .0,
        val windDirectionAngle: Double = .0,
        val windSpeed: Double = .0) {
    override fun toString(): String {
        return super.toString() + """
            temperature = $temperature
            humidity = $humidity
            pressure = $pressure
            windDirectionAngle = $windDirectionAngle
            windSpeed = $windSpeed
        """.trimIndent()
    }
}
