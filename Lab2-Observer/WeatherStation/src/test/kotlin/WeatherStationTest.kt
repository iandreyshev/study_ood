import junit.framework.TestCase.fail
import observer.IObserver
import observer.Observable
import org.junit.Test

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

    class Observer : IObserver<Any> {
        var onUpdateEvent: () -> Unit = {}

        override fun update(data: Any) = onUpdateEvent()
    }

    class Subject(override val data: Any = {}) : Observable<Any>()
}