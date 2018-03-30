abstract class BaseClass {
    protected var behavior: IBehavior? = null

    fun templateMethod() {
        println("Call template method in base class")
        somethingBaseMethod()
        behavior?.doSomething()
        println("---")
    }

    private fun somethingBaseMethod() {
        println("Call something method in vase class")
    }
}
