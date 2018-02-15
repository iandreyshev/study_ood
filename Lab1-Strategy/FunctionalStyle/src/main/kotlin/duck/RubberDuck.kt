package duck

class RubberDuck(
        quackBehavior: QuackBehavior = loudQuack(),
        swimBehavior: SwimBehavior = swimLikeDuck())
    : Duck(
        doNothing(),
        quackBehavior,
        doNothing(),
        swimBehavior) {

    override fun display() = println("I'm rubber duck")
}
