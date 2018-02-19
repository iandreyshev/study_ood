package info

data class WeatherInfo(
        val temperature: Double = .0,
        val humidity: Double = .0,
        val pressure: Double = .0,
        val windDirection: Int = 0,
        val windSpeed: Double = .0)
