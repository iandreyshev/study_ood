import junit.framework.TestCase.fail
import observer.IObserver
import observer.Observable
import org.junit.Test

class WeatherStationTest {
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

    class Observer : IObserver<Any> {
        var onUpdateEvent: () -> Unit = {}

        override fun update(data: Any) = onUpdateEvent()
    }

    class Subject(override val data: Any = {}) : Observable<Any>()
}
