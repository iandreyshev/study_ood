package beverage.cocktail

class Milkshake(size: CocktailSize = CocktailSize.STANDARD) : Cocktail("Milkshake", size) {
    override val cost: Double
        get() = 80.0
}
