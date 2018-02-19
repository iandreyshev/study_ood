import com.nhaarman.mockito_kotlin.argThat
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import info.WeatherInfo
import junit.framework.TestCase.fail
import observer.IObserver
import observer.Observable
import org.junit.Test

class WeatherStationTest {
    @Test
    fun canNotifyObserversAfterSetMeasurements() {
        val station = WeatherStation()
        val displays = ArrayList<IObserver<WeatherInfo>>()

        repeat(1000) {
            val display: IObserver<WeatherInfo> = mock()
            displays.add(display)
            station.registerObserver(display)
        }

        val newTemperature = 1.0
        val newHumidity = 2.0
        val newPressure = 3.0

        station.setMeasurements(newTemperature, newHumidity, newPressure)

        displays.forEach {
            verify(it).update(argThat {
                temperature == newTemperature && humidity == newHumidity && pressure == newPressure
            })
        }
    }

    @Test
    fun observerCanCallRemoveDuringUpdate() {
        val subject = Subject()
        val registeredObservers = ArrayList<Observer>()
        val removedObservers = ArrayList<Observer>()

        fun removeObservers(count: Int) = repeat(count) {
            if (registeredObservers.isEmpty()) {
                return@repeat
            }
            val removedObserver = registeredObservers.removeAt((0..registeredObservers.size).random)
            subject.removeObserver(removedObserver)
            removedObservers.add(removedObserver)
        }

        repeat(1000) {
            val newObserver = Observer()
            newObserver.onUpdateEvent = { removeObservers(10) }
            registeredObservers.add(newObserver)
            subject.registerObserver(newObserver)
        }

        while (!registeredObservers.isEmpty()) {
            subject.notifyObservers()
            removedObservers.forEach { it.onUpdateEvent = { fail() } }
        }
    }

    @Test
    fun notifyObserversOnlyIfDataIsDiffersFromPrevious() {
        val station = WeatherStation()
        val display: IObserver<WeatherInfo> = mock()

        station.registerObserver(display)

        repeat(1000) {
            station.setMeasurements(1.0, 1.1, 1.2, 1.3, 1.4)
        }

        verify(display).update(argThat { true })

        station.setMeasurements(2.0, 1.1, 1.2, 1.3, 1.4)

        verify(display, times(2)).update(argThat { true })
    }

    class Observer : IObserver<Any> {
        var onUpdateEvent: () -> Unit = {}

        override fun update(data: Any) = onUpdateEvent()
    }

    class Subject(override val data: Any = {}) : Observable<Any>()
}
