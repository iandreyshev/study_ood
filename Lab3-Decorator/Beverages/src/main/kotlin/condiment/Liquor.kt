package condiment

import beverage.IBeverage
import condiment.extension.ucFirst

class Liquor(beverage: IBeverage, private val mType: Type) : Condiment(beverage) {

    enum class Type {
        NUT,
        CHOCOLATE
    }

    override val condimentName: String
        get() = "${mType.name.ucFirst} liquor"
    override val condimentCost: Double
        get() = 50.0
}
