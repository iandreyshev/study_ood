package beverage.cocktail

import beverage.Beverage
import condiment.extension.ucFirst

abstract class Cocktail(description: String, private val mSize: CocktailSize) : Beverage(description) {
    val size: CocktailSize
        get() = mSize

    override val description: String
        get() = "${mSize.name.ucFirst} ${super.description}"
}
