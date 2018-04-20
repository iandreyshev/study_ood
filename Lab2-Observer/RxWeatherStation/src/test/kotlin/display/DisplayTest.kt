package display

import info.WeatherInfo
import info.WeatherInfoWithWind
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test
import java.util.*

class DisplayTest {
    private lateinit var statsDisplay: StatsDisplay

    @Before
    fun reset() {
        statsDisplay = StatsDisplay()
    }

    @Test
    fun canNotifyStatsDisplay() {
        repeat(100) {
            val data = WeatherInfo(rand(), rand(), rand())
            Observable.just(data,)
                    .subscribe(statsDisplay)
        }
    }

    @Test
    fun canNotifyDuoDisplay() {
        val innerStation = PublishSubject.create<WeatherInfo>()
        val outerStation = PublishSubject.create<WeatherInfoWithWind>()

        DisplayDuo(innerStation, outerStation)

        innerStation.onNext(WeatherInfo(rand(), rand(), rand()))
        outerStation.onNext(WeatherInfoWithWind(rand(), rand(), rand(), rand(), rand()))
    }

    private fun rand() =
            Random().nextDouble()
}