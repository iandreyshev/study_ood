package display

import info.WeatherInfo
import info.WeatherInfoWithWind
import io.reactivex.Observable

class DisplayDuo(
        innerStation: Observable<WeatherInfo>,
        outerStation: Observable<WeatherInfoWithWind>) {

    init {
        val connection = innerStation.subscribe { data ->
            data ?: return@subscribe
            onUpdate(data, innerStation)
        }

        connection.dispose()

        outerStation.subscribe { data ->
            data ?: return@subscribe
            onUpdate(data, outerStation)
        }
    }

    private fun onUpdate(data: Any, source: Observable<*>) {
        println("""
            Information from $source:
            $data
            ----------------
        """.trimIndent())
    }
}
