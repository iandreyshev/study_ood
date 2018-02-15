package duck

import org.junit.Test
import extention.play

class ModelDuckTest {
    @Test
    fun canOnlyQuack() = ModelDuck().play()
}
