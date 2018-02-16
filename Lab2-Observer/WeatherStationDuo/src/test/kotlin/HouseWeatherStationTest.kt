import observer.IObserver
import observer.ISubject
import observer.Observable
import org.junit.Assert.*
import org.junit.Test

class HouseWeatherStationTest {

    companion object {
        private const val INTERNAL_STATION_NAME = "INTERNAL station"
    }

    @Test
    fun observerCanTakeSubjectName() {
        val internalStation = InternalStation()
        val observer = Observer()

        observer.onUpdateEvent = { subject ->
            assertEquals(subject.name, INTERNAL_STATION_NAME)
        }

        internalStation.registerObserver(observer)
        internalStation.notifyObservers()
    }

    @Test
    fun observerCanCastSubjectInterfaceToConcreteSubject() {
        val internalStation = InternalStation()
        val observer = Observer()

        observer.onUpdateEvent = { subject ->
            if (subject is InternalStation) {
                assertEquals(subject, internalStation)
            } else {
                fail()
            }
        }

        internalStation.registerObserver(observer)
        internalStation.notifyObservers()
    }

    class Observer : IObserver<Any> {
        var onUpdateEvent: (subject: ISubject<Any>) -> Unit = {}
        override fun update(subject: ISubject<Any>) = onUpdateEvent(subject)
    }

    class InternalStation : Observable<Any>() {
        override val data: Any
            get() = {}

        override val name: String
            get() = INTERNAL_STATION_NAME
    }
}
