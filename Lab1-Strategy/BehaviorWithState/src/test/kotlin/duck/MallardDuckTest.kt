package duck

import org.junit.Test

class MallardDuckTest {
    @Test
    fun canCountingFlights() {
        val duck = MallardDuck()

        repeat(10) {
            duck.fly()
        }

        duck.display()
    }
}