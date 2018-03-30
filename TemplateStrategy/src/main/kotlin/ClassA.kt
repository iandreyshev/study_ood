class ClassA : BaseClass() {
    init {
        behavior = SetBehaviorABehavior()
    }

    private inner class SetBehaviorABehavior : IBehavior {
        override fun doSomething() {
            println("Change behavior to BehaviorA")
            behavior = BehaviorA()
        }
    }
}
