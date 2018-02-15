package duck

class ModelDuck(
        flyBehavior: FlyBehavior = {},
        quackBehavior: QuackBehavior = {},
        danceBehavior: DanceBehavior = {})
    : Duck(
        flyBehavior,
        quackBehavior,
        danceBehavior) {

    override fun display() = println("I'm model duck")
}
