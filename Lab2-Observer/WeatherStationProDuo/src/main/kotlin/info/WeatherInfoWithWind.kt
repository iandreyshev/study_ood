package info

class WeatherInfoWithWind(
        temperature: Double = .0,
        humidity: Double = .0,
        pressure: Double = .0,
        val windDirectionAngle: Double = .0,
        val windSpeed: Double = .0)
    : WeatherInfo(
        temperature = temperature,
        humidity = humidity,
        pressure = pressure) {

    override fun toString(): String {
        return super.toString() + """
            windDirectionAngle = $windDirectionAngle
            windSpeed = $windSpeed
        """.trimIndent()
    }
}
