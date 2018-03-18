package beverage.coffee

import org.junit.Test

class CoffeeTest {
    @Test
    fun sizeCanChangeDescription() {
        val standardLatte = Latte(CoffeeSize.STANDARD)
        println(standardLatte.description)

        val doubleLatte = Latte(CoffeeSize.DOUBLE)
        println(doubleLatte.description)
    }
}