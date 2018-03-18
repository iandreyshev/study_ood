package condiment

import beverage.IBeverage

abstract class Condiment(private val mBeverage: IBeverage) : IBeverage {

    override val description: String
        get() = "${mBeverage.description}, $condimentName"
    override val cost: Double
        get() = mBeverage.cost + condimentCost

    protected abstract val condimentName: String

    protected abstract val condimentCost: Double
}
