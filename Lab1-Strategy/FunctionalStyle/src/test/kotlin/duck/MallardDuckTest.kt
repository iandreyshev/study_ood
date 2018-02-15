package duck

import extention.play
import org.junit.Test

class MallardDuckTest {
    @Test
    fun playWithStandardDuck() {
        val duck = MallardDuck()

        repeat(5) {
            duck.play()
        }
    }

    @Test
    fun setDuckCustomBehaviors() {
        MallardDuck(
                { println("Custom flying") },
                { println("Custom QUACK!!!") },
                { println("Every day I`m shuffling") },
                { println("I can swim!") }
        ).play()
    }
}