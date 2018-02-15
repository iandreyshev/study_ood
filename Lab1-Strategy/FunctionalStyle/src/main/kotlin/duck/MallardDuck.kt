package duck

class MallardDuck(
        flyBehavior: FlyBehavior = flyWithWings(),
        quackBehavior: QuackBehavior = loudQuack(),
        danceBehavior: DanceBehavior = danceWaltz(),
        swimBehavior: SwimBehavior = swimLikeDuck())
    : Duck(
        flyBehavior,
        quackBehavior,
        danceBehavior,
        swimBehavior) {

    override fun display() = println("I'm mallard duck")
}
