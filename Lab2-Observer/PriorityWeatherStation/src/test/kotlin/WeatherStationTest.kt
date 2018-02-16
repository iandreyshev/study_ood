import junit.framework.TestCase.assertEquals
import observer.IObserver
import observer.PriorityObservable
import org.junit.Test

class WeatherStationTest {

    companion object {
        private const val OBSERVERS_COUNT = 100
        private const val NEXT_LESS = -1
        private const val NEXT_MORE = 1
        private const val NEXT_EQUALS = 0
    }

    @Test
    fun updateObserversWithEqualsPriority() {
        validatePriorityOrder(Comparator.naturalOrder(), NEXT_EQUALS, 0)
    }

    @Test
    fun updateObserversFromBigToSmallPriority() {
        validatePriorityOrder(Comparator.naturalOrder(), NEXT_LESS)
    }

    @Test
    fun updateObserversFromSmallToBigPriority() {
        validatePriorityOrder(Comparator.reverseOrder(), NEXT_MORE)
    }

    private fun validatePriorityOrder(comparator: Comparator<Int>, compareResult: Int, priorityStep: Int = 1) {
        val subject = Subject(comparator = Comparator.naturalOrder())
        var lastPriority: Int? = null

        repeat(OBSERVERS_COUNT) {
            val priority = it * priorityStep
            val observer = Observer()

            observer.onUpdateEvent = {
                if (lastPriority != null) {
                    assertEquals(compareResult, comparator.compare(lastPriority, priority))
                }
                lastPriority = priority
            }
            subject.registerObserver(observer, priority)
        }
        subject.notifyObservers()
    }

    class Subject(
            override val data: Any = {},
            comparator: Comparator<Int>) : PriorityObservable<Any>(comparator)

    class Observer : IObserver<Any> {
        var onUpdateEvent: () -> Unit = {}
        override fun update(data: Any) = onUpdateEvent()
    }
}