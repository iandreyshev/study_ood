package condiment

import beverage.IBeverage
import beverage.cocktail.Milkshake
import beverage.tea.PrincessJavaTea
import org.junit.Test

class CondimentTest {

    companion object {
        private const val A_BIT_OF_LIQUOR = 5
    }

    @Test
    fun createMilkshakeWithChocolate() {
        var beverage: IBeverage = Milkshake()
        beverage = Chocolate(beverage, 7)

        println(beverage.description)
    }

    @Test
    fun createTeaWithLiquor() {
        var beverage: IBeverage = PrincessJavaTea()

        repeat(A_BIT_OF_LIQUOR) {
            beverage = Liquor(beverage, Liquor.Type.CHOCOLATE)
        }

        println(beverage.description)
    }
}