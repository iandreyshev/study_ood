package duck

import extention.play
import org.junit.Test

class DecoyDuckTest {
    @Test
    fun playWithDuck() {
        val decoyDuck = DecoyDuck()

        decoyDuck.danceBehavior = {
            println("I`m dancing")
        }
        decoyDuck.flyBehavior = {
            println("I`m flying")
        }
        decoyDuck.quackBehavior = {
            println("Quack Quack!!!")
        }

        decoyDuck.play()
    }
}
