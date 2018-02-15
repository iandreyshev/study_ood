package duck

class ModelDuck
    : Duck(
        doNothing(),
        doNothing(),
        doNothing(),
        goToTheBottom()) {

    override fun display() = println("I'm model duck")
}
