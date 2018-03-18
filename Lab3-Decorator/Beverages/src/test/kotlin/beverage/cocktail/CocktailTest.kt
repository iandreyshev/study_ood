package beverage.cocktail

import org.junit.Test

class CocktailTest {
    @Test
    fun sizeCanChangeDescription() {
        fun printDescription(size: CocktailSize) {
            println(Milkshake(size).description)
        }

        printDescription(CocktailSize.SMALL)
        printDescription(CocktailSize.STANDARD)
        printDescription(CocktailSize.LARGE)
    }
}