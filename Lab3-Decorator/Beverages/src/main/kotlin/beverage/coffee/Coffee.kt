package beverage.coffee

import beverage.Beverage
import condiment.extension.ucFirst

abstract class Coffee(description: String, private val mSize: CoffeeSize) : Beverage(description) {
    val size: CoffeeSize
        get() = mSize

    override val description: String
        get() = "${mSize.name.ucFirst} ${super.description}"
}
