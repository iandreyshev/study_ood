package duck

class DecoyDuck(quackBehavior: QuackBehavior = loudQuack())
    : Duck(
        doNothing(),
        quackBehavior,
        doNothing(),
        goToTheBottom()) {

    override fun display() = println("I'm decoy duck")
}
