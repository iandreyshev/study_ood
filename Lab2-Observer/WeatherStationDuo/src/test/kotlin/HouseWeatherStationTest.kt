import observer.IObservable
import observer.IObserver
import observer.Observable
import org.junit.Assert.*
import org.junit.Test

class HouseWeatherStationTest {

    @Test
    fun observerCanTakeSubjectName() {
        val internalStation = InternalStation()
        val observer = Observer()

        observer.onUpdateEvent = { subject ->
            assertEquals(subject, internalStation)
        }

        internalStation.registerObserver(observer)
        internalStation.notifyObservers()
    }

    @Test
    fun observerCanCastSubjectInterfaceToConcreteSubject() {
        val station = InternalStation()
        val observer = Observer()

        observer.onUpdateEvent = { subject ->
            if (subject is InternalStation) {
                assertEquals(subject, station)
            } else {
                fail()
            }
        }

        station.registerObserver(observer)
        station.notifyObservers()
    }

    class Observer : IObserver<Any> {
        var onUpdateEvent: (subject: IObservable<Any>) -> Unit = {}
        override fun update(subject: IObservable<Any>) = onUpdateEvent(subject)
    }

    class InternalStation : Observable<Any>() {
        override val data: Any = {}
    }
}
