package duck

import extention.play
import org.junit.Test

class RubberDuckTest {
    @Test
    fun playWithStandardDuck() {
        RubberDuck().play()
    }

    @Test
    fun playWithCustomDuck() {
        RubberDuck(
                { println("Custom quack!") },
                { println("Custom swim") }
        ).play()
    }
}
