package duck

class RubberDuck(
        flyBehavior: FlyBehavior = {},
        quackBehavior: QuackBehavior = {},
        danceBehavior: DanceBehavior = {})
    : Duck(
        flyBehavior,
        quackBehavior,
        danceBehavior) {

    override fun display() = println("I'm rubber duck")
}
