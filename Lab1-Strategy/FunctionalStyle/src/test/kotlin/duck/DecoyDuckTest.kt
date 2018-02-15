package duck

import extention.play
import org.junit.Test

class DecoyDuckTest {
    @Test
    fun playWithStandardDecoyDuck() {
        DecoyDuck().play()
    }
    
    @Test
    fun setCustomQuackBehavior() {
        val decoyDuck = DecoyDuck({ println("Quack Quack!!!") })
        decoyDuck.play()
    }
}
