package duck

class MallardDuck(
        flyBehavior: FlyBehavior = {},
        quackBehavior: QuackBehavior = {},
        danceBehavior: DanceBehavior = {})
    : Duck(
        flyBehavior,
        quackBehavior,
        danceBehavior) {

    override fun display() = println("I'm mallard duck")
}
