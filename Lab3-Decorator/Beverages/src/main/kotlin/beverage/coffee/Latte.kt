package beverage.coffee

class Latte(size: CoffeeSize = CoffeeSize.STANDARD) : Coffee("Latte", size) {
    override val cost: Double
        get() = when(size) {
            CoffeeSize.STANDARD -> 90.0
            CoffeeSize.DOUBLE -> 130.0
        }
}
