import com.nhaarman.mockito_kotlin.*
import info.WeatherInfo
import observer.IObserver
import org.junit.Before
import org.junit.Test

class WeatherStationProSelectiveTest {

    private lateinit var mStation: WeatherStationProSelective

    @Before
    fun setup() {
        mStation = WeatherStationProSelective()
    }

    @Test
    fun registerAndRemoveObserverByPredicate() {
        val alwaysTrue = fun(_: WeatherInfo?, _: WeatherInfo): Boolean = true
        val observer: IObserver<WeatherInfo> = mock()

        mStation.registerObserver(alwaysTrue, observer)
        mStation.notifyObservers()
        mStation.removeObserver(alwaysTrue, observer)

        verify(observer).update(argThat { true })
    }

    @Test
    fun notRemoveObserverIfPredicateNotEqualRegisteredPredicate() {
        val notifyCount = 100
        val observer: IObserver<WeatherInfo> = mock()

        mStation.registerObserver(fun(_, _): Boolean = true, observer)
        mStation.removeObserver(fun(_, _): Boolean = true, observer)

        repeat(notifyCount) {
            mStation.notifyObservers()
        }

        verify(observer, times(notifyCount)).update(argThat { true })
    }

    @Test
    fun notifyIfOnlyOneValueChange() {
        val tempValue = 10.0
        val observer: IObserver<WeatherInfo> = mock()
        val changeTemperatureEvent = fun(latestData: WeatherInfo?, newData: WeatherInfo): Boolean {
            return latestData?.temperature != newData.temperature
        }

        mStation.setMeasurements(temperature = tempValue)
        mStation.registerObserver(changeTemperatureEvent, observer)

        repeat(1000) {
            mStation.setMeasurements(temperature = tempValue, humidity = it.toDouble())
        }

        verifyZeroInteractions(observer)

        mStation.setMeasurements(temperature = tempValue * 2)

        verify(observer).update(argThat { true })
    }
}
