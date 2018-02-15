package duck

typealias FlyBehavior = () -> Unit
typealias QuackBehavior = () -> Unit
typealias DanceBehavior = () -> Unit
typealias SwimBehavior = () -> Unit

abstract class Duck(
        private var mFlyBehavior: FlyBehavior,
        private var mQuackBehavior: QuackBehavior,
        private var mDanceBehavior: DanceBehavior,
        private val mSwimBehavior: SwimBehavior) {

    open fun quack() = mQuackBehavior()

    open fun fly() = mFlyBehavior()

    open fun dance() = mDanceBehavior()

    open fun swim() = mSwimBehavior()

    abstract fun display()
}

