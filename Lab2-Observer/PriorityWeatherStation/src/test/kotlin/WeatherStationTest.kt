import junit.framework.Assert.assertEquals
import observer.IObserver
import observer.PriorityObservable
import org.junit.Test

typealias Data = Any

class WeatherStationTest {
    @Test
    fun updateObserversFromBigToSmallPriority() {
        val observersCount = 10
        val subject = Subject(comparator = Comparator.naturalOrder())
        var updatesNum = 0

        repeat(observersCount) { priority ->
            val observer = Observer()
            observer.onUpdateEvent = {
                assertEquals(updatesNum, priority)
                updatesNum++
                println("$updatesNum, $priority")
            }

            subject.registerObserver(observer, priority)
        }

        subject.notifyObservers()
    }

    class Subject(
            override val data: Data = {},
            comparator: Comparator<Int>) : PriorityObservable<Data>(comparator)

    class Observer : IObserver<Data> {
        var onUpdateEvent: () -> Unit = {}
        override fun update(data: Data) = onUpdateEvent()
    }
}