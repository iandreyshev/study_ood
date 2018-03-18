package beverage.coffee

class Cappuccino(size: CoffeeSize = CoffeeSize.STANDARD) : Coffee("Cappuccino", size) {
    override val cost: Double
        get() = when(size) {
            CoffeeSize.STANDARD -> 80.0
            CoffeeSize.DOUBLE -> 120.0
        }
}
