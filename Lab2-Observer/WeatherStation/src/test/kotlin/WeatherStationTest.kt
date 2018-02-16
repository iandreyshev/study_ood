import junit.framework.Assert.fail
import observer.IObserver
import observer.Observable
import org.junit.Test

typealias Data = Any

class WeatherStationTest {
    @Test
    fun observerCanCallRemoveDuringUpdate() {
        val subject = Subject()
        val observer = Observer()

        subject.registerObserver(observer)

        observer.onUpdateEvent = {
            subject.removeObserver(observer)
        }

        subject.notifyObservers()

        observer.onUpdateEvent = {
            fail()
        }

        subject.notifyObservers()
    }

    class Observer : IObserver<Data> {
        var onUpdateEvent: () -> Unit = {}

        override fun update(data: Data) = onUpdateEvent()
    }

    class Subject(override val data: Data = {}) : Observable<Data>()
}