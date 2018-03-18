package condiment

import beverage.IBeverage

class Cream(beverage: IBeverage, private val mColor: Color) : Condiment(beverage) {

    enum class Color {
        WHITE,
        GREEN,
        RED;

        override fun toString(): String {
            return this.toString().toLowerCase()
        }
    }

    override val condimentName: String
        get() = "Cream($mColor)"
    override val condimentCost: Double
        get() = 25.0
}
