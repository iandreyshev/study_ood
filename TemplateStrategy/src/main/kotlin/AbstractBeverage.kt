abstract class AbstractBeverage {
    private var brewBehavior: (() -> Unit)? = null
    private var addCondimentBehavior: (() -> Unit)? = null

    fun prepare() {
        boilerWater()
        brewBehavior?.invoke()
        pourInCup()
        addCondimentBehavior?.invoke()
    }

    fun onBrew(behavior: () -> Unit) {
        brewBehavior = behavior
    }

    fun onAddCondiment(behavior: () -> Unit) {
        addCondimentBehavior = behavior
    }

    private fun boilerWater() = println("Boiling water")

    private fun pourInCup() = println("Pour into cup")
}
