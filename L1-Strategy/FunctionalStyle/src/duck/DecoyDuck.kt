package duck

class DecoyDuck(
        flyBehavior: FlyBehavior = {},
        quackBehavior: QuackBehavior = {},
        danceBehavior: DanceBehavior = {})
    : Duck(
        flyBehavior,
        quackBehavior,
        danceBehavior) {

    override fun display() = println("I'm decoy duck")
}
